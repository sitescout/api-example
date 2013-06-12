package com.sitescout.ui.api;

import com.sitescout.ui.LogProducer;
import com.sitescout.ui.qualifiers.CurrentlyActive;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Check that the app is able to connect to the API
 *
 * @author sean
 */
public class APIConnectionTest extends Arquillian {

    @Deployment
    public static WebArchive createDeployment() throws FileNotFoundException {
        File[] libs = Maven.resolver()
                .resolve(
                        "org.easytesting:fest-assert-core:LATEST",
                        "com.sitescout:dsp-core:1.6.1"
                )
                .withTransitivity().asFile();

        WebArchive archive = ShrinkWrap.create(WebArchive.class)
                .addClass(APIConnection.class)

                .addClass(LogProducer.class)
                .addPackage(CurrentlyActive.class.getPackage())  //Qualifiers
                .addPackages(true, "com.sitescout.dsp.api")

                .addAsLibraries(libs)

                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .addAsWebInfResource("faces-config.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        //archive.as(ZipExporter.class).exportTo(new File("build/test.war"));
        return archive;
    }

    @Inject APIConnection apiConnection;

    @Test
    public void testConnection() {
        apiConnection.authorize();
        assertThat(apiConnection.getAccessToken()).isNotNull();
    }
}
