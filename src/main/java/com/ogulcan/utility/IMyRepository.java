package com.ogulcan.utility;

/**
 * İlişkisel ya da ilşkisel olmayan tüm DB yapılarında
 * kullanılmak üzere genişletilebilir entegre adilebilir bir sistem
 * kullanmak için bu interface kullanılacaktır.
 * @param <T> Entity için type belirtir.(Muster,Product vs)
 * @param <ID> Entity içindeki @Id ile belirlenmiş idyi temsil eder
 *            bu id nin type girilmelidir.(Long, Strin)
 */
public interface IMyRepository <T,ID>{
}
