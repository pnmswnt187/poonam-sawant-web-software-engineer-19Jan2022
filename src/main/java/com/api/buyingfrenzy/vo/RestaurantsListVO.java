package com.api.buyingfrenzy.vo;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class RestaurantsListVO extends RepresentationModel<RestaurantsListVO> {
	
	private List<RestaurantsVO> restaurantsVO;

	public List<RestaurantsVO> getRestaurantsVO() {
		return restaurantsVO;
	}

	public void setRestaurantsVO(List<RestaurantsVO> restaurantsVO) {
		this.restaurantsVO = restaurantsVO;
	}

}
