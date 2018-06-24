package com.cy.rms.basedata.domain;

public class Test {

	public static void main(String[] args) {

		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select p.id , p.name , p.priceIn ,p.priceOut , p.stock , p.sell ,p.category_id , p.factory_id , ")
				.append("c.categoryName as categoryName ,  f.factoryName as factoryName ")
				.append("from product p, category c, factory f ")
				.append("where p.category_id = c.id and p.factory_id = f.id  ");
		
		System.out.println(sbSql.toString());
	}
}
