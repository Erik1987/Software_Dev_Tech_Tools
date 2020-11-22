
/**
 * 
 * Järjestelmässämme on yksi moottoripyörä (vm. 2015, ajetut kilometrit
 * 20500 ja se ei ole kevytmoottoripyörä).
 * 
 * Järjestelmässä on myös yksi auto (vm. 2018, ajetut kilometrit 
 * 56000 ja se on farmariauto).
 * 
 * Sinun tehtäväsi olisi refaktoroida tiedostossa olevat luokat
 * siten, että järjestelmän arkkitehtuuri seuraa
 * Dependency inversion-periaatetta (DIP) hyödyntäen olioiden
 * monimuotoisuutta ja samalla 
 * BusinessLuokalla voidaan käsitellä sekä Moottoripyöriä että Autoja.
 * Itse BusinessLuokasta voi olla järjestelmässä useampi instanssi, 
 * toinen moottoripyörille ja toinen autoille.
 * 
 * Kaikki luokat on kirjoitettu tehtävän palautuksen helpottamiseksi samaan
 * tiedostoon.
 * 
 * Tiedoston voi kääntää komennolla: "javac DIPTehtava.java"
 * Kun kääntäminen on onnistunut, niin luodun .class-luokan voi ajaa 
 * komennolla: "java DIPTehtava".
 */

import java.util.Calendar;

/**
 * Tämä on ohjelman käyttöliittymä, joka tulostaa haluamamme asiat. Tätä luokkaa
 * ei tarvitse muuttaa.
 */
public class DIPTehtava {

    private static Sovellus sovellusAutolle = DIPConfigurator.konfiguraatioAutolle();

    private static Sovellus sovellusMoottoripyoralle = DIPConfigurator.konfiguraatioMoottoripyorille();

    public static void main(final String[] args) {
        System.out.println("Järjestelmän autolla on " + "ajettu keskimäärin vuodessa: "
                + sovellusAutolle.getBusinessLuokkaKeskimaaraisilleKM().laskeKeskimaaraisetKilometritPerVuosi()
                + " kilometriä");

        System.out.println("Järjestelmän auton ajoneuvovero on "
                + sovellusAutolle.getBusinessLuokkaVeroLaskuri().laskeAjoneuvoVero() + " euroa");

        System.out.println("Järjestelmän moottoripyörälla on " + "ajettu keskimäärin vuodessa: "
                + sovellusMoottoripyoralle.getBusinessLuokkaKeskimaaraisilleKM().laskeKeskimaaraisetKilometritPerVuosi()
                + " kilometriä");

        System.out.println("Järjestelmän moottoripyörän ajoneuvovero on "
                + sovellusMoottoripyoralle.getBusinessLuokkaVeroLaskuri().laskeAjoneuvoVero() + " euroa");
    }
}

class Sovellus {
    private VeroLaskuri businessLuokkaVeroLaskuri;

    private BusinessLuokkaKeskimaaraisilleKM businessLuokkaKM;

    public void setVeroLaskuri(VeroLaskuri businessLuokkaVeroLaskuri) {
        this.businessLuokkaVeroLaskuri = businessLuokkaVeroLaskuri;
    }

    public void setBusinessLuokka(BusinessLuokkaKeskimaaraisilleKM businessLuokkaKM) {
        this.businessLuokkaKM = businessLuokkaKM;
    }

    public VeroLaskuri getBusinessLuokkaVeroLaskuri() {
        return this.businessLuokkaVeroLaskuri;
    }

    public BusinessLuokkaKeskimaaraisilleKM getBusinessLuokkaKeskimaaraisilleKM() {
        return this.businessLuokkaKM;
    }

}

/**
 * Tämän konfiguraattoriluokan tehtävä on rakentaa sovelluksen konfiguraatio.
 * Jos sovelluksesta halutaan erilainen konfiguraatio, niin tarvitsee muuttaa
 * vain tätä luokkaa, ei itse sovelluksen luokkia. Sovelluksen määrittelyt
 * voitaisiin myös antaa esimerkiksi XML-tiedostossa, jonka tämä luokka sitten
 * lataisi ja käsittelisi. Esim. Springissä on vastaava luokka.
 */
class DIPConfigurator {

    public static Sovellus konfiguraatioMoottoripyorille() {
        final BusinessLuokkaKeskimaaraisilleKM businessLuokkaMoottoripyoralle = new BusinessLuokkaKeskimaaraisilleKM();
        final Moottoripyora moottoriPyora = new Moottoripyora(20500, 2015, false);
        businessLuokkaMoottoripyoralle.setAjoneuvo(moottoriPyora);

        final BusinessMoottoripyoraVero businessMoottoripyoraVero = new BusinessMoottoripyoraVero();
        businessMoottoripyoraVero.setMoottoripyora(moottoriPyora);

        final Sovellus sovellusMoottoriPyoralle = new Sovellus();
        sovellusMoottoriPyoralle.setBusinessLuokka(businessLuokkaMoottoripyoralle);
        sovellusMoottoriPyoralle.setVeroLaskuri(businessMoottoripyoraVero);

        return sovellusMoottoriPyoralle;
    }

    public static Sovellus konfiguraatioAutolle() {
        final BusinessLuokkaKeskimaaraisilleKM businessLuokkaAutolle = new BusinessLuokkaKeskimaaraisilleKM();
        final Auto auto = new Auto(56000, 2018, true);
        businessLuokkaAutolle.setAjoneuvo(auto);

        final BusinessAutoVero businessAutoVero = new BusinessAutoVero();
        businessAutoVero.setAuto(auto);

        final Sovellus sovellusAutolle = new Sovellus();
        sovellusAutolle.setBusinessLuokka(businessLuokkaAutolle);
        sovellusAutolle.setVeroLaskuri(businessAutoVero);

        return sovellusAutolle;
    }
}

/**
 * Ajoneuvo-rajapinta
 */
interface Ajoneuvo {
    public int getAjetutKilometrit();

    public int getVuosimalli();
}

/**
 * Auto-luokka toteuttaa Ajoneuvo-rajapinnan asioita ja lisäksi sillä on
 * pelkästään Auto-tyyppiin liittyviä asioita 1 kpl.
 */
class Auto implements Ajoneuvo {
    private final int km;
    private final int vuosimalli;
    private final boolean onkoFarmari;

    public Auto(final int km, final int vuosimalli, final boolean onkoFarmari) {
        this.km = km;
        this.vuosimalli = vuosimalli;
        this.onkoFarmari = onkoFarmari;
    }

    public int getAjetutKilometrit() {
        return this.km;
    }

    public int getVuosimalli() {
        return this.vuosimalli;
    }

    public boolean onkoFarmari() {
        return this.onkoFarmari;
    }
}

/**
 * Moottoripyora-luokka toteuttaa Ajoneuvo-rajapinnan asioita ja lisäksi sillä
 * on pelkästään Moottoripyora-tyyppiin liittyviä asioita 1 kpl.
 */
class Moottoripyora implements Ajoneuvo {
    private final int km;
    private final int vuosimalli;
    private final boolean onkoKevytMoottoripyora;

    public Moottoripyora(final int km, final int vuosimalli, final boolean onkoKevytMoottoripyora) {
        this.km = km;
        this.vuosimalli = vuosimalli;
        this.onkoKevytMoottoripyora = onkoKevytMoottoripyora;
    }

    public int getAjetutKilometrit() {
        return km;
    }

    public int getVuosimalli() {
        return vuosimalli;
    }

    public boolean onkoKevytMoottoripyora() {
        return this.onkoKevytMoottoripyora;
    }
}

class BusinessMoottoripyoraVero implements VeroLaskuri {

    private Moottoripyora moottoripyora;

    @Override
    public double laskeAjoneuvoVero() {
        double jakoArvo = this.moottoripyora.onkoKevytMoottoripyora() ? 2 : 1;
        return StaticHelperFunctions.laskeVuosimaara(moottoripyora.getVuosimalli()) * 100 / jakoArvo;
    }

    public void setMoottoripyora(Moottoripyora moottoripyora) {
        this.moottoripyora = moottoripyora;
    }
}

class StaticHelperFunctions {

    // Hyvä käytäntö vain staattisia metodeja sisältävälle luokalle, sitä ei ole
    // tarkoitus initialisoida
    private StaticHelperFunctions() {
        throw new AssertionError();
    }

    public static int laskeVuosimaara(int vuosi) {
        return (Calendar.getInstance().get(Calendar.YEAR) - vuosi);
    }
}

class BusinessAutoVero implements VeroLaskuri {

    private Auto auto;

    @Override
    public double laskeAjoneuvoVero() {
        double kerroin = this.auto.onkoFarmari() ? 1.2 : 1;
        return StaticHelperFunctions.laskeVuosimaara(auto.getVuosimalli()) * 100 * kerroin;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
}

interface VeroLaskuri {
    public double laskeAjoneuvoVero();
}

/**
 * Tämä luokka sisältää järjestelmän businesslogiikan, eli oikestaan sen kaikean
 * kiinnostavan järjestelmässä, mitä tuoteomistaja on meille määritellyt.
 */
class BusinessLuokkaKeskimaaraisilleKM {
    private Ajoneuvo ajoneuvo;

    /**
     * Tämä on järjestelmän ydinbusinekseen liittyvä tarpeellinen toiminto. Tämän
     * metodin voi olettaa toimivan logiikaltaan oikein, mutta se saattaa tarvita
     * jotain pientä päivitystä muutujien nimiin.
     * 
     * @return Palauta keskimäärin joka vuosi ajetut kilometrit auton käyttöönoton
     *         jälkeen
     */
    public int laskeKeskimaaraisetKilometritPerVuosi() {
        return ajoneuvo.getAjetutKilometrit()
                / ((Calendar.getInstance().get(Calendar.YEAR) - ajoneuvo.getVuosimalli()) + 1);
    }

    public void setAjoneuvo(final Ajoneuvo ajoneuvo) {
        this.ajoneuvo = ajoneuvo;
    }
}
