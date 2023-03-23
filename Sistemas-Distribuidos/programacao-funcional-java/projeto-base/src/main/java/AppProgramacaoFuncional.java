import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Predicate;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

/**
 * Aplicação de exemplo de princípios de programação funcional em Java,
 * Expressões Lambda e API de Streams do Java 8.
 *
 * Para aprofundar nestes assuntos, veja os links abaixo:
 *
 * <ul>
 * <li><a href=
 * "https://www.oracle.com/technetwork/pt/articles/java/streams-api-java-8-3410098-ptb.html">Curso
 * Streams e Expressões Lambda do Java 8</a></li>
 * <li><a href= "http://bit.ly/2I2U5bU">Curso JDK 8 MOOC: Lambdas and Streams
 * Introduction</a></li>
 * </ul>
 *
 * @author Manoel Campos da Silva Filho
 */
public class AppProgramacaoFuncional {
    private static final int TOTAL_STUDENTS = 1_000_000;
    private final List<Student> students;

    public static void main(String[] args) {
        new AppProgramacaoFuncional();

    }
    public AppProgramacaoFuncional(){
        students = StudentGenerator.generate(TOTAL_STUDENTS);
//        students.stream()
//                        .filter(Student::isFemale)
//                                .forEach(System.out::println);

    /*
        final List<Student> listMulheres = students.stream()
                .filter(s -> s.getGender() == 'F')
                .toList();
        listMulheres.forEach(System.out::println);

        final long count = students.stream()
                .filter(Student::isFemale)
                .count();
        System.out.println(count);
    */
        final double inicio = System.currentTimeMillis();
        students.stream()
                .parallel()
                .filter(Student::isFemale)
                .mapToDouble(Student::getScore)
                .map(nota -> nota * 10)
                .filter(nota -> nota >= 60)
                .average()
                .ifPresent(media -> System.out.printf("Média [F] : %.2f\n",media));

        final double fim = System.currentTimeMillis();
        System.out.println("Tempo gasto: "+ (fim - inicio)/1000);

        Map<Course, Double> collecteStudantes = students
                .stream()
                .filter(Student::hasCourse)
                .collect(
                        groupingBy(
                                Student::getCourse,
                                averagingDouble(Student::getScore)
                        )
                );

        //collecteStudantes.forEach((course, average) -> System.out.println(course.getName() + ": " + average));

        /*
        paraiso
        palmas
        */

    }//class AppProgramacaoFuncional


}//class

/*
Predicate é


 */