package org.redhat.ba;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 * This is a sample class to test some rules.
 */
public class AppTest {

    static KieBase kbase;
    static KieSession ksession;
    static KieRuntimeLogger klogger;

    @BeforeClass
    public static void setupKsession() {
        try {
            // load up the knowledge base and create session
            ksession = readKnowledgeBase();
            klogger = KieServices.Factory.get().getLoggers().newFileLogger(ksession, "audit");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @AfterClass
    public static void closeKsession() {
        try {
            // closing resources
            klogger.close();
            ksession.dispose();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Test
    public void HelloWorldTest() {

        //now create some test data
    	
    	Greeting greeting = new Greeting();
        // insert objects into working memory
        FactHandle greetingFH = ksession.insert(greeting);
        ksession.fireAllRules();
        ksession.delete(greetingFH);

        assertEquals(new String("Hello World!"), greeting.getGreeting());
    }

   

    private static KieSession readKnowledgeBase() throws Exception {
        
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession();
        
        return kSession;
    }

}


