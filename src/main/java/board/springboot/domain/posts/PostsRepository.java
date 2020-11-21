package board.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository
 * - 보통 MyBatis 등에서 Dao라 불리는 DB Layer 접근자이다.
 * - JPA에서는 Repository라고 부르며, Interface로 생성한다.
 * - 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다(대단하네...)
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
