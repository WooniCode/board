package board.springboot.domain.posts;

import board.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// TIP: 주요 어노테이션을 클래스에 가깝게 두면 무엇이 필수 어노테이션인지 쉽게 알 수 있음.

/**
 * @Entity
 * - 테이블과 링크될 클래스임을 나타낸다.
 * - 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭한다.
 * ex) SalesManager.java -> sales_manager table
 *
 * 👀 Setter
 * - Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
 * - 해당 클래스의 인스턴스 값들이 언제 어디서 변해야하는지 코드상으로 명확하기 구분하기 힘들고, 차후 기능 변경 시 복잡해진다.
 *
 *
 * @NoArgsConstructor
 * - 기본 생성자 자동 추가
 * - public Posts(){} 와 같은 효과
 *
 * @Getter
 * - 클래스 내 모든 필드의 Getter 메소드를 자동 생성
 */
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    /**
     * @Id
     * - 해당 테이블의 PK 필드를 나타낸다.
     *
     * @GeneratedValue
     * - PK의 생성규칙을 나타낸다.
     * - Spring Boots 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.
     * - 2.0버전과 1.5 버전 차이는 이 링크에서 확인: https://jojoldu.tistory.com/295
     * - 왠만하면 Entity의 PK는 Auto.increment를 추천한다. 근거는 다음과 같음
     *  1) FK를 맺을 때 다른 테이블에서 복합키 전부를 갖고 있거나, 중간 테이블을 하나 더 둬야 하는 상황이 발생한다.
     *  2) 인덱스에 좋은 영향을 끼치지 못한다.
     *  3) 유니크한 조건이 변경될 경우 PK 전체를 수정해야 하는 일이 발생한다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column
     * - 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
     * - 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
     * - 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex: title), 타입을 TEXT로 변경하고 싶거나(ex:content) 등의 경우에 사용한다.
     */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /**
     * @Builder
     * - 해당 클래스의 빌더 패턴 클래스를 생성
     * - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
     * - 빌더를 사용하게 되면 어느 필드에 어떤 값을 채워 넣어야 할지 명확하게 인지할 수 있어 유지보수가 쉽고 실수가 줄어든다.
     */
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
