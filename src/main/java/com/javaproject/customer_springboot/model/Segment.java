package com.javaproject.customer_springboot.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SEGMENTO_CLIENTE")
public class Segment {

	@Id
	private List<Long> id;
	
	@Column(name = "DESCRICAO_SEGMENTO")
	private String segmentDescription;
	
	@Column(name = "PERCENTUAL_LUCRO_SEGMENTO")
	private String segmentPercentualValue;
}
