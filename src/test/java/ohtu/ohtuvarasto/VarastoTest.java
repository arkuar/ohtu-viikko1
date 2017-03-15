package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuontiNegatiivisella() {
        Varasto uusi = new Varasto(-10);
        assertEquals(0, uusi.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoSaldolla() {
        Varasto uusi = new Varasto(10, 10);
        assertEquals(10, uusi.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, uusi.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoNegatiivisellaSaldollaJaTilavuudella() {
        Varasto uusi = new Varasto(-1, -1);
        assertEquals(0, uusi.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, uusi.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoYlimaaraSaldolla() {
        Varasto uusi = new Varasto(5, 10);
        assertEquals(5, uusi.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, uusi.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaNegatiivinenVarastoon() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaYlimaaraVarastoon() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaNegatiivinenVarastosta() {
        assertEquals(0.0, varasto.otaVarastosta(-20), vertailuTarkkuus);
    }
    
    @Test
    public void otaEnemmanKuinVarastossaOn() {
        varasto.lisaaVarastoon(10);
        double otettu = varasto.otaVarastosta(20);
        assertEquals(10, otettu, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoPalauttaaOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
}
