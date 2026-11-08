// Package: el dossier/espace de noms w matricule  eli fih el classe hedhi (ykhali el projet menadhem)
package com.example.douanetemporelle;
// Déclaration mte3 el classe "Cles": public ya3ni ay classe o5ra fil projet tnajem testa3malha
// Hedhi classe utilitaire, tlem el clés/constantes bech nesta3mlouhom fi blayes o5ra

public class Cles {
    // Constante MATRICULE: public = tetchef men ay blasa, static = ma testha9ech objet bech nesta3mlouha (Cles.MATRICULE),
    // final = el valeur "1234" ma tetbadalch ba3d ma tet7at
    public static final String MATRICULE = "1234";
    // Méthode "cle": static = nesta3mlouha direct (Cles.cle(...)) men ghir ma na3mlou objet,
    // trajja3 String (texte), w tekhou  paramètres: el matricule w el nom

    public static String cle(String matricule, String nom) {
        // traja3lk el matricule + "_" + el nom concatenee par un separateur eli howa tiret bas  , mathalan "1234_Ali"
        return matricule + "_" + nom;
    }
}
