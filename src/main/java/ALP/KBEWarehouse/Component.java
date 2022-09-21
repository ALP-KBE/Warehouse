package ALP.KBEWarehouse;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representation of a component of a guitar
 */
@Entity
@Table(name = "components")
public class Component implements Serializable {

    @Column(name = "komponententyp")
    private String komponententyp;
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "material")
    private String material;
    @Column(name = "herkunft")
    private String herkunft;
    @Column(name = "farbe")
    private String farbe;
    @Column(name = "hersteller")
    private String hersteller;
    @Column(name = "form")
    private String form;
    @Column(name = "gebrauchszustand")
    private String gebrauchszustand;
    @Column(name = "preis")
    private String preis;
    @Column(name = "verfuegbarkeit")
    private String verfuegbarkeit;
    @Column(name = "klangauswirkung")
    private String klangauswirkung;

    public Component() {
        this.komponententyp = "";
        this.name = "";
        this.material = "";
        this.herkunft = "";
        this.farbe = "";
        this.hersteller = "";
        this.form = "";
        this.gebrauchszustand = "";
        this.preis = "";
        this.verfuegbarkeit = "";
        this.klangauswirkung = "";
    }

    public Component(String komponententyp,
            String name,
            String material,
            String herkunft,
            String farbe,
            String hersteller,
            String form,
            String gebrauchszustand,
            String preis,
            String verfuegbarkeit,
            String klangauswirkung) {
        this.komponententyp = komponententyp;
        this.name = name;
        this.material = material;
        this.herkunft = herkunft;
        this.farbe = farbe;
        this.hersteller = hersteller;
        this.form = form;
        this.gebrauchszustand = gebrauchszustand;
        this.preis = preis;
        this.verfuegbarkeit = verfuegbarkeit;
        this.klangauswirkung = klangauswirkung;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHerkunft() {
        return this.herkunft;
    }

    public void setHerkunft(String herkunft) {
        this.herkunft = herkunft;
    }

    public String getFarbe() {
        return this.farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public String getHersteller() {
        return this.hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getForm() {
        return this.form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getGebrauchszustand() {
        return this.gebrauchszustand;
    }

    public void setGebrauchszustand(String gebrauchszustand) {
        this.gebrauchszustand = gebrauchszustand;
    }

    public String getPreis() {
        return this.preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public String getVerfuegbarkeit() {
        return this.verfuegbarkeit;
    }

    public void setVerfuegbarkeit(String verfuegbarkeit) {
        this.verfuegbarkeit = verfuegbarkeit;
    }

    public String getKlangauswirkung() {
        return this.klangauswirkung;
    }

    public void setKlangauswirkung(String klangauswirkung) {
        this.klangauswirkung = klangauswirkung;
    }

    public String getKomponententyp() {
        return this.komponententyp;
    }

    public void setKomponententyp(String komponententyp) {
        this.komponententyp = komponententyp;
    }

    @Override
    public String toString() {
        return getKomponententyp() + ", " +
                getName() + ", " +
                getMaterial() + ", " +
                getHerkunft() + ", " +
                getFarbe() + ", " +
                getHersteller() + ", " +
                getForm() + ", " +
                getGebrauchszustand() + ", " +
                getPreis() + ", " +
                getVerfuegbarkeit() + ", " +
                getKlangauswirkung() + ", ";
    }
}