package testP;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class Java8_test {

	@Test
	public void testSort() {
		List<User> users = getAllUsers();
//		users.stream().sorted((user1,user2) -> Integer.compare(user1.getAge(), user2.getAge())).forEach(user -> System.out.println(user.getName()));
	}
	
	private List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		users.add(new User("333",18, "b"));
		users.add(new User("111",17, "c"));
		users.add(new User("222",15, "a"));
		return users;
	}
	
	@Test
	public void filter() {
		List<User> users = getAllUsers();
		int sum = users.stream()
                .mapToInt(c -> c.getAge())
                .sum();
//		System.out.println("年龄总和："+sum);
//		System.out.println("符合个数：" + users.stream().filter(user -> user.getAge()>17).count());
	}
	
	@Test
	public void reduce() {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");
		list.add("perl");
		list.add("c");
		list.add("lisp");
		list.add("c#");
		Stream<String> wordStream = list.stream();
		Stream<Integer> lengthStream = wordStream.map(s -> s.length());
		Optional<Integer> sum = lengthStream.reduce((x, y) -> x + y);
//		sum.ifPresent(System.out::println);

	}
	
	@Test
	public void typeOfStream(){
		IntStream.of(new int[]{1, 2, 3}).forEach(System.out::print); //123
		IntStream.range(1, 3).forEach(System.out::print); //12 
//		IntStream.rangeClosed(1, 3).forEach(System.out::print);// 123
		
//		Stream<List<Integer>> inputStream = Stream.of(
//				 Arrays.asList(1),
//				 Arrays.asList(2, 3),
//				 Arrays.asList(4, 5, 6)
//				 );
//				Stream<Integer> outputStream = inputStream.
//				flatMap((childList) -> childList.stream());
//				outputStream.forEach(index -> System.out.println(index));
	}
	
	private class User {
		String id;
		int age;
		String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public User(String id, int age, String name) {
			super();
			this.id = id;
			this.age = age;
			this.name = name;
		}
		
	} 
}
