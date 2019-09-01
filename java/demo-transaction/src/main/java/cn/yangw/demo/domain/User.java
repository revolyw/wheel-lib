package cn.yangw.demo.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * TODO comments
 *
 * @author Willow
 * @date 2019-09-01
 */
@Data
@Entity
@Table(name = "user")
@DynamicInsert
@DynamicUpdate
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "modify_time")
	private Date modifyTime;
}
