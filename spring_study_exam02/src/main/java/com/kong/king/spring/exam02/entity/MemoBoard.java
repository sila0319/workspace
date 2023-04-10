package com.kong.king.spring.exam02.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;





@Entity
@Table(name="memoboard")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoBoard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //DB의 성격에 맞게 알아서 동작하도록 마리아디비는 오토인크리먼트, 오라클은 시퀀스를 만들어서 동작한다.
	private Long mid;
	
	@Column(length = 200, nullable= false)
	private String content;
}
