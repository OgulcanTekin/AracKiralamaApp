package com.ogulcan.utility;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class MyFactoryRepository <T,ID> implements ICrud<T,ID>{
   private EntityManager entityManager;
   private CriteriaBuilder criteriaBuilder;
   private Session ss;
   private Transaction tt;
   private T t;



    public MyFactoryRepository(T t){
       entityManager = HibernateUtility.getSessionFactory().createEntityManager();
       criteriaBuilder = entityManager.getCriteriaBuilder();
       this.t=t;
   }
   public void openSession(){
       ss=HibernateUtility.getSessionFactory().openSession();
       tt=ss.beginTransaction();
   }

   public void closeSession(){
       tt.commit();
       ss.close();
   }

    @Override
    public <S extends T> S save(S entity) {
        try{
            openSession();
            ss.save(entity);
            closeSession();
            return entity;
        }catch (Exception exception){
            tt.rollback();
            throw exception;
        }
    }
    @Override
    public <S extends T> S update(S entity) {
        try{
            openSession();
            ss.update(entity);
            closeSession();
            return entity;
        }catch (Exception exception){
            tt.rollback();
            throw exception;
        }
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        try{
            openSession();
            entities.forEach(entity->{
                ss.save(entity);
            });
            closeSession();
            return entities;
        }catch (Exception exception){
            tt.rollback();
            throw exception;
        }
    }
    @Override
    public <S extends T> Iterable<S> updateAll(Iterable<S> entities) {
        try{
            openSession();
            entities.forEach(entity->{
                ss.update(entity);
            });
            closeSession();
            return entities;
        }catch (Exception exception){
            tt.rollback();
            throw exception;
        }
    }

    @Override
    public void delete(T entity) {
        try{
            openSession();
            ss.delete(entity);
            closeSession();
        }catch (Exception exception){
            throw exception;
        }
    }

    @Override
    public void deleteById(ID id) {
        try {
            CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
            Root<T> root = (Root<T>) criteria.from(t.getClass());
            criteria.select(root);
            criteria.where(criteriaBuilder.equal(root.get("id"), id));
            T deleteEntity = entityManager.createQuery(criteria).getSingleResult();
            openSession();
            ss.delete(deleteEntity);
            closeSession();
        }catch (Exception exception){
            throw exception;
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>)criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>)criteria.from(t.getClass());
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get("id"),id));
        List<T> result = entityManager.createQuery(criteria).getResultList();
        if(result.isEmpty()) return Optional.empty();
        return Optional.of(result.get(0));
   }

    @Override
    public boolean existById(ID id) {
        try {
            CriteriaQuery<T> criteria = (CriteriaQuery<T>)criteriaBuilder.createQuery(t.getClass());
            Root<T> root = (Root<T>)criteria.from(t.getClass());
            criteria.select(root);
            criteria.where(criteriaBuilder.equal(root.get("id"),id));
            List<T> result = entityManager.createQuery(criteria).getResultList();
            return !result.isEmpty();
        }catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>)criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>)criteria.from(t.getClass());
        criteria.select(root);
        List<T> result = entityManager.createQuery(criteria).getResultList();
        return result;
    }

    @Override
    public List<T> findAllByColumnNameAndValue(String columnName, String columnValue) {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>)criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>)criteria.from(t.getClass());
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get(columnName),columnValue));
        List<T> result= entityManager.createQuery(criteria).getResultList();
        return result;
    }

    /**
     * reflections api kullan??lacak.
     * reverse engineering -> Tersine M??hendislik
     * AMA??: bize verilen bir nesne i??indeki dolu alanlara g??re filtreleme yapmak
     * Musteri nesnesi oldu??unu d??????nelim.
     * ad = "ahmet", adres="Ankara" b??yle bir nesnemiz olsun.
     * burada i??inde de??er olan alanlar?? se??ip i??inde de??er olmayan, null olan alanlar?? ge??miyoruz.
     * de??er olan alanlar?? kullanarak kriter olu??turuyoruz. burada kriter i??in 2 de??ere ihtiya?? var
     * kolon ad?? ve de??eri , kolon ad?? olarak de??i??kenin ad??n??,
     * kolon de??eri olarak de??i??kenin
     * de??erini al??yoruz. b??ylece esnek bir filtreleme yapm???? oluyoruz.
     * @param entity
     * @return
     */

    @Override
    public List<T> findByEntity(T entity) {
        List<T> result=null;
        Class cl = entity.getClass();
        Field[] fl = cl.getDeclaredFields();
        try{
            CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
            Root<T> root = (Root<T>)criteria.from(t.getClass());
            criteria.select(root);
            List<Predicate> list = new ArrayList<>(); // kriter listesini tutacak
            for(int i=0; i<fl.length;i++){
                fl[i].setAccessible(true); // eri??mek istedi??im alan e??er eri??ime kapal?? (private ise) a????yoruz.
                if (fl[i].get(entity) != null && !fl[i].getName().equals("id")) {
                    /**
                     * Boolean, String, Long vs bu t??rlerin sorgular?? farkl?? olacakt??r. bu nedenle bunlar??
                     * kontrol etmeniz gerekmektedir.
                     * String ad " ogulcan"; -> DataType (Stirng.class), DataName(ad), DataValue(ogulcan)
                     * ad="%og%"
                     */
                    if (fl[i].getType().isAssignableFrom(String.class))
                        list.add(criteriaBuilder.like(root.get(fl[i].getName()),"%"+fl[i].get(entity)+"%"));
                    else if(fl[i].getType().isAssignableFrom(Long.class) && !fl[i].get(entity).equals(0))
                        list.add(criteriaBuilder.equal(root.get(fl[i].getName()), fl[i].get(entity)));
                    else
                        list.add(criteriaBuilder.equal(root.get(fl[i].getName()), fl[i].get(entity)));
                }
            }
            criteria.where(list.toArray(new Predicate[]{}));
            result = entityManager.createQuery(criteria).getResultList();
        }catch (Exception exception) {
            System.out.println("Beklenmeyen bir hata olu??tu....: "+ exception.toString());
        }
        return result;
    }




}
