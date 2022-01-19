package com.api.buyingfrenzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.api.buyingfrenzy.entities.Menu;
import com.api.buyingfrenzy.entities.PurchaseHistory;
import com.api.buyingfrenzy.entities.Restaurants;
import com.api.buyingfrenzy.entities.Users;
import com.api.buyingfrenzy.service.RestaurantsService;
import com.api.buyingfrenzy.service.UsersService;
import com.api.buyingfrenzy.vo.UsersVO;

@ExtendWith({
	SpringExtension.class
	, RestDocumentationExtension.class
})
@AutoConfigureRestDocs(
	outputDir = "build/generated-snippets"
)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT
	)
class BuyingfrenzyApplicationTests {
	
	private static Restaurants restaurant;
	private static List<Restaurants> restaurants;
	private static List<Menu> menus;
	private static Menu menu;
	
	static {
		
		restaurants = new ArrayList<>();
		
		/**Rest 1**/
		restaurant = new Restaurants();
		restaurant.setCashBalance(1828.78);
		
		menus = new ArrayList<>();
		menu =new Menu();
		menu.setDishName("Kase-Torte");
		menu.setPrice(11.88);
		menus.add(menu);
		
		menu =new Menu();
		menu.setDishName("Postum cereal coffee");
		menu.setPrice(13.88);
		
		restaurant.setMenu(menus);
		restaurant.setOpeningHours("Sun, Tues 3:15 pm - 1:15 am / Weds 11:30 am - 5:30 pm / Thurs 5:30 pm - 11:15 pm / Fri 2 pm - 3:15 am / Sat 2 pm - 3 pm");
		restaurant.setRestaurantName("Seasons of Durango");
		
		restaurants.add(restaurant);
		
		/**Rest 2**/
		restaurant = new Restaurants();
		restaurant.setCashBalance(4483.84);
		
		menus = new ArrayList<>();
		menu =new Menu();
		menu.setDishName("Coffee Cocktail (Port Wine");
		menu.setPrice(12.45);
		menus.add(menu);
		
		menu =new Menu();
		menu.setDishName("La Romaine Braisee au Fond de Veau");
		menu.setPrice(10.59);
		
		restaurant.setMenu(menus);
		restaurant.setOpeningHours("Sun, Tues 3:15 pm - 1:15 am / Tues 11 am - 2 pm / Weds 1:15 pm - 3:15 am / Thurs 10 am - 3:15 am / Sat 5 am - 11:30 am / Sun 10:45 am - 5 pm");
		restaurant.setRestaurantName("'Ulu Ocean Grill and Sushi Lounge");
		
		restaurants.add(restaurant);
	}
	
	private static Users users;
	private static List<PurchaseHistory> purchaseHistory;
	private static PurchaseHistory ph;
	
	static {
		users = new Users();
		users.setCashBalance(700.7);
		users.setId(1);
		users.setName("Edith Johnson");
		purchaseHistory = new ArrayList<>();
		ph = new PurchaseHistory();
		ph.setDishName("Kase-Torte");
		ph.setRestaurantName("Seasons of Durango");
		ph.setTransactionAmount(13.18);
		ph.setTransactionDate("02/10/2020 04:09 AM");
		purchaseHistory.add(ph);
		users.setPurchaseHistory(purchaseHistory);
	}
	
	private MockMvc mockMvc;
	
	@Autowired
    private RestaurantsService restaurantsService;
	
	@Autowired
    private UsersService usersService;
    
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	 @Autowired
	 private RestDocumentationContextProvider restDocumentation;       
	 
	@BeforeEach
    void setup() {
		this.mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }
	
	@WithAnonymousUser
	@WithMockUser(
			username = "username"
		)
	@Test
	void getRestaurantsByOpeningHrs() throws Exception {
		
		this.restaurantsService.deleteAll();
		this.restaurantsService.save(restaurants);
		
		this.mockMvc.perform(
				get("/restaurants?openingHours=Sun, Tues 3:15 pm - 1:15 am")
				.header("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
				.andExpect(status().isOk())
			    .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
				.andDo(document("{methodName}", 
						preprocessRequest(prettyPrint())
						, preprocessResponse(prettyPrint())
						, requestParameters(
							parameterWithName("openingHours").description("Opening Hours").optional()
								)
						, requestHeaders(headerWithName("Authorization").description("Basic auth credentials"))
						, responseFields(
				            fieldWithPath("[]").description("Restaurant Name")
				        )
				    )
				);
	}
	
	@WithMockUser(
			username = "username"
		)
	@Test
	void getRestaurantsByParam() throws Exception {
		
		this.restaurantsService.deleteAll();
		this.restaurantsService.save(restaurants);
		
		this.mockMvc.perform(
				get("/restaurants/toprestaurants?y=1&x=2&inputCondition=grtthen")
				.header("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
				.andExpect(status().isOk())
			    .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
				.andDo(document("{methodName}", 
						preprocessRequest(prettyPrint())
						, preprocessResponse(prettyPrint())
						, requestParameters(
							parameterWithName("y").description("List top y restaurants").optional(),
							parameterWithName("x").description("Restaurants that have more or less than x number of dishes within a price range").optional(),
							parameterWithName("inputCondition").description("Input for Condition more or less than").optional()
								)
						, requestHeaders(headerWithName("Authorization").description("Basic auth credentials"))
						, responseFields(
				            fieldWithPath("[]").description("Restaurant Name")
				        )
				    )
				);
	}
	
	@WithMockUser(
			username = "username"
		)
	@Test
	void getRestaurantsByRelevance() throws Exception {
		
		this.restaurantsService.deleteAll();
		this.restaurantsService.save(restaurants);
		
		this.mockMvc.perform(
				get("/restaurants/relevance?columnname=restaurant&matchstring=Durango")
				.header("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
				.andExpect(status().isOk())
			    .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
				.andDo(document("{methodName}", 
						preprocessRequest(prettyPrint())
						, preprocessResponse(prettyPrint())
						, requestParameters(
							parameterWithName("columnname").description("Search for relevant Restaurant").optional(),
							parameterWithName("matchstring").description("Pass String").optional()
								)
						, requestHeaders(headerWithName("Authorization").description("Basic auth credentials"))
						, responseFields(
				            fieldWithPath("[]").description("Restaurant Name")
				        )
				    )
				);
	}
	
	@WithMockUser(
			username = "username"
		)
	@Test
	void getDishByRelevance() throws Exception {
		
		this.restaurantsService.deleteAll();
		this.restaurantsService.save(restaurants);
		
		this.mockMvc.perform(
				get("/restaurants/relevance?columnname=dish&matchstring=Torte")
				.header("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
				.andExpect(status().isOk())
			    .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
				.andDo(document("{methodName}", 
						preprocessRequest(prettyPrint())
						, preprocessResponse(prettyPrint())
						, requestParameters(
							parameterWithName("columnname").description("Search for relevant Dish").optional(),
							parameterWithName("matchstring").description("Pass String").optional()
								)
						, requestHeaders(headerWithName("Authorization").description("Basic auth credentials"))
						, responseFields(
				            fieldWithPath("[]").description("Dish Name")
				        )
				    )
				);
	}
	
	@WithMockUser(
			"username"
		)
	@Test
	void updateBuyDishTransaction() throws Exception {
		
		this.restaurantsService.deleteAll();
		this.usersService.deleteAll();
		
		this.restaurantsService.save(restaurants);
		this.usersService.save(users);
		
		int id = 1;
		String dishName = "Kase-Torte";
		String restaurantName="Seasons of Durango";
	       			
		this.mockMvc.perform( 
			      put("/users/{id}?dishName="+dishName+"&restaurantName="+restaurantName, id)
			      .header("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ=")
			      .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(MockMvcResultMatchers.status().isAccepted())
				  .andDo(document("{methodName}"
		    		, preprocessRequest(prettyPrint())
					, preprocessResponse(prettyPrint())
					, pathParameters(
						parameterWithName("id").description("User Id")
			        )
					,requestParameters(
						parameterWithName("dishName").description("Dish Name"),
						parameterWithName("restaurantName").description("Restaurant Name")	
					)
					, requestHeaders(headerWithName("Authorization").description("Basic auth credentials"))
			    )
			);
		}
	
	@WithMockUser(
			"username"
		)
	@Test
	void transactionUsingInvalidUser() throws Exception {
		
		this.restaurantsService.deleteAll();
		this.usersService.deleteAll();
		
		this.restaurantsService.save(restaurants);
		this.usersService.save(users);
		
		int id = 2;
		String dishName = "Kase-Torte";
		String restaurantName="Seasons of Durango";
	       			
		this.mockMvc.perform( 
			      put("/users/{id}?dishName="+dishName+"&restaurantName="+restaurantName, id)
			      .header("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ=")
			      .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(MockMvcResultMatchers.status().isNotFound())
				  .andDo(document("{methodName}"
		    		, preprocessRequest(prettyPrint())
					, preprocessResponse(prettyPrint())
					, pathParameters(
						parameterWithName("id").description("User Id")
			        )
					,requestParameters(
						parameterWithName("dishName").description("Dish Name"),
						parameterWithName("restaurantName").description("Restaurant Name")	
					)
					, requestHeaders(headerWithName("Authorization").description("Basic auth credentials"))
			    )
			);
		}
	
	@WithMockUser(
			"username"
		)
	@Test
	void transactionUsingInvalidRestaurant() throws Exception {
		
		this.restaurantsService.deleteAll();
		this.usersService.deleteAll();
		
		this.restaurantsService.save(restaurants);
		this.usersService.save(users);
		
		int id = 1;
		String dishName = "Kase-Torte";
		String restaurantName="Nandos";
	       			
		this.mockMvc.perform( 
			      put("/users/{id}?dishName="+dishName+"&restaurantName="+restaurantName, id)
			      .header("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ=")
			      .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(MockMvcResultMatchers.status().isNotFound())
				  .andDo(document("{methodName}"
		    		, preprocessRequest(prettyPrint())
					, preprocessResponse(prettyPrint())
					, pathParameters(
						parameterWithName("id").description("User Id")
			        )
					,requestParameters(
						parameterWithName("dishName").description("Dish Name"),
						parameterWithName("restaurantName").description("Restaurant Name")	
					)
					, requestHeaders(headerWithName("Authorization").description("Basic auth credentials"))
			    )
			);
		}

}
