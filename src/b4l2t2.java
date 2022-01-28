import java.util.*;
import java.util.stream.Collectors;

public class b4l2t2 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        System.out.println("Несовершеннолетних: " + persons.stream().filter(x -> x.getAge() < 18).count());
// теперь тут будут фамилии призывников
        families = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
//        System.out.println("Тестовый вывод призывников");
//        families.stream().limit(9).forEach(System.out::println);

// теперь тут будет список потенциально работоспособных людей
        persons = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() >= 18 && ((x.getSex() == Sex.WOMAN && x.getAge() <= 60) || (x.getSex() == Sex.MAN && x
                        .getAge() <= 65)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
//        System.out.println("Тестовый вывод потенциально работоспособных людей");
//        persons.stream()
//                .limit(9)
//                .forEach(x -> System.out.println(x.getFamily() + " " + x.getAge() + " " + x.getSex() + " " + x.getEducation()));
    }
}
