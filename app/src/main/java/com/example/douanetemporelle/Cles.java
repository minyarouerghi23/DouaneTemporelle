package com.example.douanetemporelle;

public class Cles {

    // 1) آخر 4 أرقام من بطاقة الطالب (بدّلها برقمك) / à remplacer par tes 4 chiffres
    public static final String MATRICULE = "1234";

    // 2) دالة ثابتة تبني المفتاح: الرقم + "_" + الاسم
    public static String cle(String matricule, String nom) {
        return matricule + "_" + nom;
    }
}
