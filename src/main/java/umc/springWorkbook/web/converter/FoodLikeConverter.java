package umc.springWorkbook.web.converter;

import umc.springWorkbook.domain.Food;
import umc.springWorkbook.domain.mapping.FoodLike;

import java.util.List;
import java.util.stream.Collectors;

public class FoodLikeConverter {

    public static List<FoodLike> toFoodLikeList(List<Food> foodList) {
        return foodList.stream()
                .map(food ->
                        FoodLike.builder()
                                .food(food)
                                .build()
                ).collect(Collectors.toList());
    }
}
