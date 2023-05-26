package hello.core.singleton;

public class SingletonService {
	//static이기 때문에 딱 하나만 생성
	private static final SingletonService instance = new SingletonService();

	//인스턴스의 참조를 꺼낼수 있는 방법은 이것 밖에 없다.
	public static SingletonService getInstance() {
		return instance;
	}

	//Java가 뜰때, 내부적으로 자기 자신객체를 생성해서 멤버변수에 넣어두고, 조회할때는 메소드로 호출
	private SingletonService() {
	}

	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}
}
