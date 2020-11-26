package tipalti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import tipalti.model.Address;
import tipalti.model.Name;
import tipalti.model.Person;
import tipalti.service.StructureService;

@ComponentScan
public class RunMe {

    @Autowired
    StructureService service;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RunMe.class);
        RunMe bean = ctx.getBean(RunMe.class);
        bean.run();
        ctx.close();

    }

    public void run() {


        Name nameA = new Name("aa", "AA");

        Name nameB = new Name("bb", "BB");
        Name nameC = new Name("bb", "BB");

        Name nameD = new Name("cc", "CC");
        Name nameE = new Name("cc", "CC");

        Name nameF = new Name("dd", "DD");
        Name nameG = new Name("dd", "DD");

        Name nameH = new Name("ee", "EE");
        Name nameI = new Name("ee", "EE");

        Name nameX = new Name("xx", "XX");

        Address addressA = new Address("b", "B");
        Address addressB = new Address("b", "B");
        Address addressC = new Address("c", "C");
        Address addressD = new Address("c", "C");
        Address addressE = new Address("d", "D");
        Address addressF = new Address("d", "D");
        Address addressG = new Address("e", "E");
        Address addressH = new Address("e", "E");
        Address addressI = new Address("f", "F");


        Address addressX = new Address("z", "Z");


        Person personA = new Person(nameA, addressA);
        Person personB = new Person(nameB, addressB);
        Person personC = new Person(nameC, addressC);
        Person personD = new Person(nameD, addressD);
        Person personE = new Person(nameE, addressE);
        Person personF = new Person(nameF, addressF);
        Person personG = new Person(nameG, addressG);
        Person personH = new Person(nameH, addressH);
        Person personI = new Person(nameI, addressI);

        Person personNotConnect = new Person(nameX, addressX);

        Person[] people = new Person[]{personA, personB, personC,
                personD, personD, personE, personF,
                personG, personH, personI,
                personNotConnect};


        service.init(people);

        System.out.println("Path between A->A: " + service.findMinRelationLevel(personA, personA));
        System.out.println("Path between A->B: " + service.findMinRelationLevel(personA, personB));
        System.out.println("Path between A->C: " + service.findMinRelationLevel(personA, personC));
        System.out.println("Path between A->D: " + service.findMinRelationLevel(personA, personD));
        System.out.println("Path between A->E: " + service.findMinRelationLevel(personA, personE));
        System.out.println("Path between A->F: " + service.findMinRelationLevel(personA, personF));
        System.out.println("Path between A->G: " + service.findMinRelationLevel(personA, personG));
        System.out.println("Path between A->H: " + service.findMinRelationLevel(personA, personH));
        System.out.println("Path between A->I: " + service.findMinRelationLevel(personA, personI));


        System.out.println("Path between A->X: " + service.findMinRelationLevel(personA, personNotConnect));
        System.out.println("Path between B->X: " + service.findMinRelationLevel(personB, personNotConnect));
        System.out.println("Path between C->X: " + service.findMinRelationLevel(personC, personNotConnect));
        System.out.println("Path between D->X: " + service.findMinRelationLevel(personD, personNotConnect));
        System.out.println("Path between E->X: " + service.findMinRelationLevel(personE, personNotConnect));
        System.out.println("Path between F->X: " + service.findMinRelationLevel(personF, personNotConnect));
        System.out.println("Path between G->X: " + service.findMinRelationLevel(personG, personNotConnect));
        System.out.println("Path between H->X: " + service.findMinRelationLevel(personH, personNotConnect));
        System.out.println("Path between I->X: " + service.findMinRelationLevel(personI, personNotConnect));
        System.out.println("Path between X->I: " + service.findMinRelationLevel(personNotConnect, personI));
    }
}
