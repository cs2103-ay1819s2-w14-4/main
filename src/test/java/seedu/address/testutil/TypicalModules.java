package seedu.address.testutil;

import java.util.Arrays;
import java.util.LinkedList;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredits;
import seedu.address.model.module.ModuleDepartment;
import seedu.address.model.module.ModuleDescription;
import seedu.address.model.module.ModulePrereq;
import seedu.address.model.module.ModuleTitle;

/**
 * A class that stores some examples of Typical Modules
 */
public class TypicalModules {

    public static final Module CS1010 = new Module(new ModuleCode("CS1010"),
            new ModuleTitle("Programming Methodology"),
            new ModuleDescription(
                    "This module introduces the fundamental concepts of problem solving by "
                            + "computing and programming using an imperative programming language. "
                            + "It is the first and foremost introductory course to computing. "
                            + "Topics covered include computational thinking and computational problem solving, "
                            + "designing and specifying an algorithm, basic problem formulation "
                            + "and problem solving approaches, program development, "
                            + "coding, testing and debugging, fundamental programming constructs "
                            + "(variables, types, expressions, assignments, functions, control structures, etc.), "
                            + "fundamental data structures (arrays, strings, composite data types), "
                            + "basic sorting, and recursion."
            ),
            new ModulePrereq("Nil", new LinkedList<Module>()),
            new ModuleDepartment("Computer Science"), new ModuleCredits("4"));

    public static final Module CS2030 = new Module(new ModuleCode("CS2030"),
            new ModuleTitle("Programming Methodology II"),
            new ModuleDescription(
                    "This module is a follow up to CS1010. "
                            + "It explores two modern programming paradigms, "
                            + "object-oriented programming and functional programming. "
                            + "Through a series of integrated assignments, "
                            + "students will learn to develop medium-scale software programs in the "
                            + "order of thousands of lines of code and tens of classes using objectoriented "
                            + "design principles and advanced programming constructs "
                            + "available in the two paradigms. Topics include objects and classes, "
                            + "composition, association, inheritance, "
                            + "interface, polymorphism, abstract classes, dynamic binding, lambda expression, "
                            + "effect-free programming, first class functions, "
                            + "closures, continuations, monad, etc."
            ),
            new ModulePrereq("CS1010", new LinkedList<Module>(Arrays.asList(CS1010))),
            new ModuleDepartment("Computer Science"), new ModuleCredits("4"));


    public static final Module CS2040 = new Module(new ModuleCode("CS2040"),
            new ModuleTitle("Data Structures and Algorithms"),
            new ModuleDescription(
                    "This module introduces students to the design and implementation "
                            + "of fundamental data structures and algorithms. "
                            + "The module covers basic data structures "
                            + "(linked lists, stacks, queues, hash tables, binary heaps, trees, and graphs), "
                            + "searching and sorting algorithms, and basic analysis of algorithms."),
            new ModulePrereq("CS1010",
                    new LinkedList<Module>(Arrays.asList(CS1010))),
            new ModuleDepartment("Computer Science"), new ModuleCredits("4"));

    public static final Module CS2040C = new Module(new ModuleCode("CS2040C"),
            new ModuleTitle("Data Structures and Algorithms"),
            new ModuleDescription(
                    "This module introduces students to the design and implementation "
                            + "of fundamental data structures and algorithms. "
                            + "The module covers basic data structures "
                            + "(linked lists, stacks, queues, hash tables, binary heaps, trees, and graphs), "
                            + "searching and sorting algorithms, basic analysis of algorithms, "
                            + "and basic object-oriented programming concepts."
            ),
            new ModulePrereq("CS1010",
                    new LinkedList<Module>(Arrays.asList(CS1010))),
            new ModuleDepartment("Computer Science"), new ModuleCredits("4"));

    public static final Module CS2103T = new Module(new ModuleCode("CS2103T"),
            new ModuleTitle("Software Engineering"),
            new ModuleDescription(
                    "This module introduces the necessary conceptual and analytical tools for "
                            + "systematic and rigorous development of software systems. "
                            + "It covers four main areas of software development, "
                            + "namely object-oriented system analysis, "
                            + "object-oriented system modelling and design, implementation, "
                            + "and testing, with emphasis on system modelling and design and "
                            + "implementation of software modules that work cooperatively "
                            + "to fulfill the requirements of the system. "
                            + "Tools and techniques for software development, "
                            + "such as Unified Modelling Language (UML), program specification, and testing methods, "
                            + "will be taught. Major software engineering issues such as modularisation criteria, "
                            + "program correctness, and software quality will also be covered."),
            new ModulePrereq("For SoC students only. "
                    + "(CS1020 or CS1020E or CS2020) or (CS2030 and (CS2040 or CS2040C))",
                    new LinkedList<Module>(Arrays.asList(CS2030, CS2040, CS2040C))),
            new ModuleDepartment("Computer Science"), new ModuleCredits("4"));

    public static final Module MA1512 = new Module(new ModuleCode("MA1512"), new ModuleTitle("Calculus for Computing"),
            new ModuleDescription(
                    "This module provides a basic foundation for calculus "
                            + "and its related subjects required by computing students. "
                            + "The objective is to "
                            + "train the students to be able to handle calculus techniques "
                            + "arising in their courses of specialization. "
                            + "In addition to the standard calculus material, "
                            + "the course also covers simple mathematical modeling techniques and "
                            + "numerical methods in connection with ordinary differential equations. "
                            + "Major topics: Preliminaries on sets and number systems. "
                            + "Calculus of functions of one variable and applications. "
                            + "Sequences, series and power series. Functions of several variables. Extrema. "
                            + "First and second order differential equations. "
                            + "Basic numerical methods for ordinary differential equations."
            ),
            new ModulePrereq(
                    "GCE ‘A’ level Mathematics or H2 Mathematics or H2 Further Mathematics",
                    new LinkedList<Module>()),
            new ModuleDepartment("Computer Science"), new ModuleCredits("4"));


    public static final Module LSM1301 = new Module(new ModuleCode("LSM1301"),
            new ModuleTitle("General Biology"),
            new ModuleDescription(
                    "This is an introductory module that explores what a living thing is, "
                            + "the basics of life, and the science behind it. "
                            + "The course will introduce the chemistry "
                            + "of life and the unit of life. "
                            + "The question of how traits are inherited will be discussed "
                            + "and the field of biotechnology, "
                            + "including its applications and the ethical issues "
                            + "involved be will introduced. "
                            + "The diversity of life on earth will be explored, "
                            + "with discussions how life on earth possibly came about "
                            + "and how biologists try to classify "
                            + "and make sense of the diversity. "
                            + "The course will also introduce the "
                            + "concept of life functions from cells to tissues "
                            + "and from organs to systems. The concept of how organisms maintain their "
                            + "internal constancy and organisation of major organ systems will be discussed. "
                            + "The focus will be to introduce the unifying concepts in biology "
                            + "and how they play a role in everyday life."),
            new ModulePrereq("Nil", new LinkedList<Module>()),
            new ModuleDepartment(("Life Science")), new ModuleCredits("4"));

    private TypicalModules() {
    }

    public static LinkedList<Module> getTypicalModules() {
        return new LinkedList<>(Arrays.asList(CS1010, CS2030, CS2040, CS2040C, CS2103T, LSM1301));
    }
}
