package in.asish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.asish.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	List<Task> findByUserUid(Integer uid);
	Task findByUserUidAndId(Integer uid,Integer id);
}
