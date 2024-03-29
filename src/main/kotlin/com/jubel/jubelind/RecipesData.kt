package com.jubel.jubelind

import com.jubel.jubelind.products.domain.Product
import com.jubel.jubelind.products.domain.ProductRepository
import com.jubel.jubelind.recipes.domain.ProductGrams
import com.jubel.jubelind.recipes.domain.Recipe
import com.jubel.jubelind.recipes.domain.RecipeId

class RecipesData {

    fun get(productRepository: ProductRepository): List<Recipe>{

        return getAuxRecipesStores().groupBy { it.name }.map {
            Recipe(
                RecipeId(),
                it.key,
                it.value.map {recipeStore ->
                    ProductGrams(productRepository.getByName(recipeStore.productName), recipeStore.grams)
                }
            )
        }

    }

    private fun getAuxRecipesStores(): List<AuxRecipeStore> {

        return listOf(
            AuxRecipeStore("Arroz a la cubana", "Aceite de oliva", 5),
            AuxRecipeStore("Arroz a la cubana", "Albahaca fresca", 0),
            AuxRecipeStore("Arroz a la cubana", "Arroz estándar", 80),
            AuxRecipeStore("Arroz a la cubana", "Huevo crudo entero", 60),
            AuxRecipeStore("Arroz a la cubana", "Orégano", 0),
            AuxRecipeStore("Arroz a la cubana", "Sal de mesa", 0),
            AuxRecipeStore("Arroz a la cubana", "Tomate triturado (Hacendado)", 100),
            AuxRecipeStore(
                "Arroz con guisantes, seitán, zanahoria, pimiento, maiz y salsa de soja",
                "Aceite de oliva",
                4
            ),
            AuxRecipeStore("Arroz con guisantes, seitán, zanahoria, pimiento, maiz y salsa de soja", "Ajo", 4),
            AuxRecipeStore(
                "Arroz con guisantes, seitán, zanahoria, pimiento, maiz y salsa de soja",
                "Arroz basmati",
                40
            ),
            AuxRecipeStore("Arroz con guisantes, seitán, zanahoria, pimiento, maiz y salsa de soja", "Cebollino", 0),
            AuxRecipeStore(
                "Arroz con guisantes, seitán, zanahoria, pimiento, maiz y salsa de soja",
                "Guisantes congelados (Hacendado)",
                40
            ),
            AuxRecipeStore(
                "Arroz con guisantes, seitán, zanahoria, pimiento, maiz y salsa de soja",
                "Maíz dulce (Hacendado)",
                20
            ),
            AuxRecipeStore(
                "Arroz con guisantes, seitán, zanahoria, pimiento, maiz y salsa de soja",
                "Pimiento fresco o congelado",
                80
            ),
            AuxRecipeStore(
                "Arroz con guisantes, seitán, zanahoria, pimiento, maiz y salsa de soja",
                "Salsa de soja (Hacendado)",
                10
            ),
            AuxRecipeStore("Arroz con guisantes, seitán, zanahoria, pimiento, maiz y salsa de soja", "Seitán", 100),
            AuxRecipeStore("Arroz con guisantes, seitán, zanahoria, pimiento, maiz y salsa de soja", "Zanahoria", 60),
            AuxRecipeStore("Arroz con pollo, calabación, cebolla y anacardos", "Aceite de oliva", 5),
            AuxRecipeStore("Arroz con pollo, calabación, cebolla y anacardos", "Ajo", 5),
            AuxRecipeStore("Arroz con pollo, calabación, cebolla y anacardos", "Anacardos natural", 10),
            AuxRecipeStore("Arroz con pollo, calabación, cebolla y anacardos", "Arroz basmati", 50),
            AuxRecipeStore("Arroz con pollo, calabación, cebolla y anacardos", "Calabacín", 150),
            AuxRecipeStore("Arroz con pollo, calabación, cebolla y anacardos", "Cebolla", 100),
            AuxRecipeStore("Arroz con pollo, calabación, cebolla y anacardos", "Pechuga de pollo", 150),
            AuxRecipeStore("Arroz con pollo, calabación, cebolla y anacardos", "Pimienta negra", 0),
            AuxRecipeStore("Arroz con pollo, calabación, cebolla y anacardos", "Sal de mesa", 0),
            AuxRecipeStore("Arroz integral con salmón y brócoli", "Aceite de oliva", 8),
            AuxRecipeStore("Arroz integral con salmón y brócoli", "Arroz integral (Hacendado)", 42),
            AuxRecipeStore("Arroz integral con salmón y brócoli", "Brócoli fresco o congelado", 120),
            AuxRecipeStore("Arroz integral con salmón y brócoli", "Pimienta negra", 0),
            AuxRecipeStore("Arroz integral con salmón y brócoli", "Sal de mesa", 0),
            AuxRecipeStore("Arroz integral con salmón y brócoli", "Salmón fresco", 120),
            AuxRecipeStore("Ayuno", "Agua", 0),
            AuxRecipeStore("Bacalao a la plancha con crema de espárragos", "Bacalao fresco", 150),
            AuxRecipeStore("Bacalao a la plancha con crema de espárragos", "Crema de espárragos", 350),
            AuxRecipeStore("Berenjena rellena de soja texturizada", "Aceite de oliva", 5),
            AuxRecipeStore("Berenjena rellena de soja texturizada", "Ajo", 5),
            AuxRecipeStore("Berenjena rellena de soja texturizada", "Berenjena", 200),
            AuxRecipeStore("Berenjena rellena de soja texturizada", "Cebolla", 100),
            AuxRecipeStore("Berenjena rellena de soja texturizada", "Orégano", 0),
            AuxRecipeStore("Berenjena rellena de soja texturizada", "Pimienta negra", 0),
            AuxRecipeStore("Berenjena rellena de soja texturizada", "Queso mozzarella, Perlas (Hacendado)", 15),
            AuxRecipeStore("Berenjena rellena de soja texturizada", "Sal de mesa", 0),
            AuxRecipeStore("Berenjena rellena de soja texturizada", "Soja texturizada", 60),
            AuxRecipeStore("Berenjena rellena de soja texturizada", "Tomate triturado (Hacendado)", 100),
            AuxRecipeStore("Bocadillo de lomo, queso y tomate", "Lomo de cerdo magro", 120),
            AuxRecipeStore("Bocadillo de lomo, queso y tomate", "Pan blanco", 110),
            AuxRecipeStore("Bocadillo de lomo, queso y tomate", "Queso lonchas light (Havarti)", 20),
            AuxRecipeStore("Bocadillo de lomo, queso y tomate", "Tomate", 80),
            AuxRecipeStore("Comida libre 1000 cals", "ProductOne", 925),
            AuxRecipeStore("Cous cous con lagostinos y calabacín", "Aceite de oliva", 8),
            AuxRecipeStore("Cous cous con lagostinos y calabacín", "Ajo", 10),
            AuxRecipeStore("Cous cous con lagostinos y calabacín", "Calabacín", 120),
            AuxRecipeStore("Cous cous con lagostinos y calabacín", "Cous cous (Bia, Mercadona)", 45),
            AuxRecipeStore(
                "Cous cous con lagostinos y calabacín",
                "Langostino crudo pelado congelado (Pescanova)",
                100
            ),
            AuxRecipeStore("Cous cous con lagostinos y calabacín", "Mantequilla", 3),
            AuxRecipeStore("Cous cous con lagostinos y calabacín", "Perejil", 0),
            AuxRecipeStore("Cous cous con lagostinos y calabacín", "Sal de mesa", 0),
            AuxRecipeStore("Cous cous con salmón, calabacín, pimiento y zanahoria", "Aceite de oliva", 5),
            AuxRecipeStore("Cous cous con salmón, calabacín, pimiento y zanahoria", "Agua", 45),
            AuxRecipeStore("Cous cous con salmón, calabacín, pimiento y zanahoria", "Calabacín", 80),
            AuxRecipeStore("Cous cous con salmón, calabacín, pimiento y zanahoria", "Cous cous (Bia, Mercadona)", 45),
            AuxRecipeStore("Cous cous con salmón, calabacín, pimiento y zanahoria", "Pimiento fresco o congelado", 80),
            AuxRecipeStore("Cous cous con salmón, calabacín, pimiento y zanahoria", "Sal de mesa", 0),
            AuxRecipeStore("Cous cous con salmón, calabacín, pimiento y zanahoria", "Salmón fresco", 150),
            AuxRecipeStore("Cous cous con salmón, calabacín, pimiento y zanahoria", "Zanahoria", 80),
            AuxRecipeStore("Cous cous de coliflor con pollo y curry", "Aceite de oliva", 5),
            AuxRecipeStore("Cous cous de coliflor con pollo y curry", "Coliflor congelada", 250),
            AuxRecipeStore("Cous cous de coliflor con pollo y curry", "Curry", 0),
            AuxRecipeStore("Cous cous de coliflor con pollo y curry", "Pechuga de pollo", 200),
            AuxRecipeStore("Cous cous de coliflor con pollo y curry", "Sal de mesa", 0),
            AuxRecipeStore("Dorada al horno con patatas y cebolla", "Aceite de oliva", 5),
            AuxRecipeStore("Dorada al horno con patatas y cebolla", "Cebolla", 50),
            AuxRecipeStore("Dorada al horno con patatas y cebolla", "Dorada fresca", 250),
            AuxRecipeStore("Dorada al horno con patatas y cebolla", "Patata", 100),
            AuxRecipeStore("Dorada al horno con patatas y cebolla", "Perejil", 0),
            AuxRecipeStore("Dorada al horno con patatas y cebolla", "Sal de mesa", 0),
            AuxRecipeStore("Emperador a la plancha / Hervido valenciano", "Aceite de oliva", 5),
            AuxRecipeStore("Emperador a la plancha / Hervido valenciano", "Emperador", 180),
            AuxRecipeStore("Emperador a la plancha / Hervido valenciano", "Judia verde", 80),
            AuxRecipeStore("Emperador a la plancha / Hervido valenciano", "Patata", 100),
            AuxRecipeStore("Emperador a la plancha / Hervido valenciano", "Perejil", 1),
            AuxRecipeStore("Emperador a la plancha / Hervido valenciano", "Sal de mesa", 1),
            AuxRecipeStore("Emperador a la plancha / Hervido valenciano", "Vinagre de vino blanco", 5),
            AuxRecipeStore("Emperador a la plancha / Hervido valenciano", "Zanahoria", 50),
            AuxRecipeStore("Emperador a la plancha / Hervido valenciano", "Huevo crudo entero", 0),
            AuxRecipeStore("Emperador plancha y hervido valenciano", "Aceite de oliva", 5),
            AuxRecipeStore("Emperador plancha y hervido valenciano", "Cebolla", 50),
            AuxRecipeStore("Emperador plancha y hervido valenciano", "Emperador", 180),
            AuxRecipeStore("Emperador plancha y hervido valenciano", "Judia verde", 80),
            AuxRecipeStore("Emperador plancha y hervido valenciano", "Patata", 100),
            AuxRecipeStore("Emperador plancha y hervido valenciano", "Perejil", 0),
            AuxRecipeStore("Emperador plancha y hervido valenciano", "Sal de mesa", 0),
            AuxRecipeStore("Emperador plancha y hervido valenciano", "Vinagre módena", 0),
            AuxRecipeStore("Emperador plancha y hervido valenciano", "Zanahoria", 50),
            AuxRecipeStore("Ensalada de alubias, atún, huevo duro, tomate y canónigos", "Aceite de oliva", 5),
            AuxRecipeStore("Ensalada de alubias, atún, huevo duro, tomate y canónigos", "Alubia blanca cocida", 145),
            AuxRecipeStore("Ensalada de alubias, atún, huevo duro, tomate y canónigos", "Atún en aceite oli lata", 60),
            AuxRecipeStore("Ensalada de alubias, atún, huevo duro, tomate y canónigos", "Cherry", 80),
            AuxRecipeStore("Ensalada de alubias, atún, huevo duro, tomate y canónigos", "Hoja verde", 30),
            AuxRecipeStore("Ensalada de alubias, atún, huevo duro, tomate y canónigos", "Huevo crudo entero", 60),
            AuxRecipeStore("Ensalada de alubias, atún, huevo duro, tomate y canónigos", "Pepino", 0),
            AuxRecipeStore(
                "Ensalada de alubias, atún, huevo duro, tomate y canónigos",
                "Pimiento fresco o congelado",
                80
            ),
            AuxRecipeStore("Ensalada de alubias, atún, huevo duro, tomate y canónigos", "Vinagre módena", 0),
            AuxRecipeStore("Ensalada de lentejas, queso fresco, maíz, tomate, cebolla y rúcula", "Aceite de oliva", 5),
            AuxRecipeStore("Ensalada de lentejas, queso fresco, maíz, tomate, cebolla y rúcula", "Cebolla", 50),
            AuxRecipeStore(
                "Ensalada de lentejas, queso fresco, maíz, tomate, cebolla y rúcula",
                "Lentejas cocidas",
                150
            ),
            AuxRecipeStore(
                "Ensalada de lentejas, queso fresco, maíz, tomate, cebolla y rúcula",
                "Maíz dulce (Hacendado)",
                50
            ),
            AuxRecipeStore(
                "Ensalada de lentejas, queso fresco, maíz, tomate, cebolla y rúcula",
                "Queso fresco 0MG (Burgo de Arias)",
                120
            ),
            AuxRecipeStore(
                "Ensalada de lentejas, queso fresco, maíz, tomate, cebolla y rúcula",
                "Rúcula (Hacendado)",
                50
            ),
            AuxRecipeStore("Ensalada de lentejas, queso fresco, maíz, tomate, cebolla y rúcula", "Sal de mesa", 0),
            AuxRecipeStore("Ensalada de lentejas, queso fresco, maíz, tomate, cebolla y rúcula", "Tomate", 150),
            AuxRecipeStore("Ensalada de lentejas, queso fresco, maíz, tomate, cebolla y rúcula", "Vinagre módena", 5),
            AuxRecipeStore("Ensalada de pollo, huevo, tomate, picatostes y mozzarella", "Aceite de oliva", 5),
            AuxRecipeStore("Ensalada de pollo, huevo, tomate, picatostes y mozzarella", "Huevo crudo entero", 60),
            AuxRecipeStore("Ensalada de pollo, huevo, tomate, picatostes y mozzarella", "Lechuga iceberg", 60),
            AuxRecipeStore("Ensalada de pollo, huevo, tomate, picatostes y mozzarella", "Pechuga de pollo", 100),
            AuxRecipeStore(
                "Ensalada de pollo, huevo, tomate, picatostes y mozzarella",
                "Picatostes tostados (Hacendado)",
                10
            ),
            AuxRecipeStore(
                "Ensalada de pollo, huevo, tomate, picatostes y mozzarella",
                "Queso mozzarella, Perlas (Hacendado)",
                15
            ),
            AuxRecipeStore("Ensalada de pollo, huevo, tomate, picatostes y mozzarella", "Tomate", 80),
            AuxRecipeStore("Ensalada de pollo, queso azul, manzana, uvas, espinacas y apio", "Aceite de oliva", 5),
            AuxRecipeStore("Ensalada de pollo, queso azul, manzana, uvas, espinacas y apio", "Apio", 80),
            AuxRecipeStore(
                "Ensalada de pollo, queso azul, manzana, uvas, espinacas y apio",
                "Espinacas normales o baby (Hacendado)",
                60
            ),
            AuxRecipeStore("Ensalada de pollo, queso azul, manzana, uvas, espinacas y apio", "Manzana", 150),
            AuxRecipeStore("Ensalada de pollo, queso azul, manzana, uvas, espinacas y apio", "Pechuga de pollo", 150),
            AuxRecipeStore("Ensalada de pollo, queso azul, manzana, uvas, espinacas y apio", "Queso azul", 40),
            AuxRecipeStore("Ensalada de pollo, queso azul, manzana, uvas, espinacas y apio", "Sal de mesa", 0),
            AuxRecipeStore("Ensalada de pollo, queso azul, manzana, uvas, espinacas y apio", "Uva", 0),
            AuxRecipeStore("Garbanzos con pollo, cebolla y bacon al curry", "Bacon", 20),
            AuxRecipeStore("Garbanzos con pollo, cebolla y bacon al curry", "Caldo de pollo (Gallina Blanca)", 60),
            AuxRecipeStore("Garbanzos con pollo, cebolla y bacon al curry", "Cebolla", 60),
            AuxRecipeStore("Garbanzos con pollo, cebolla y bacon al curry", "Curry", 2),
            AuxRecipeStore(
                "Garbanzos con pollo, cebolla y bacon al curry",
                "Garbanzo cocido pedrosillano (Hacendado)",
                150
            ),
            AuxRecipeStore("Garbanzos con pollo, cebolla y bacon al curry", "Pechuga de pollo", 150),
            AuxRecipeStore("Garbanzos con pollo, cebolla y bacon al curry", "Vino blanco (cocinar)", 100),
            AuxRecipeStore("Guiso de garbanzos con ternera, zanahoria y pimiento", "Aceite de oliva", 5),
            AuxRecipeStore("Guiso de garbanzos con ternera, zanahoria y pimiento", "Ajo", 6),
            AuxRecipeStore(
                "Guiso de garbanzos con ternera, zanahoria y pimiento",
                "Garbanzo cocido pedrosillano (Hacendado)",
                130
            ),
            AuxRecipeStore("Guiso de garbanzos con ternera, zanahoria y pimiento", "Pimentón", 0),
            AuxRecipeStore("Guiso de garbanzos con ternera, zanahoria y pimiento", "Pimiento fresco o congelado", 70),
            AuxRecipeStore("Guiso de garbanzos con ternera, zanahoria y pimiento", "Sal de mesa", 0),
            AuxRecipeStore("Guiso de garbanzos con ternera, zanahoria y pimiento", "Ternera", 150),
            AuxRecipeStore("Guiso de garbanzos con ternera, zanahoria y pimiento", "Tomate triturado (Hacendado)", 50),
            AuxRecipeStore("Guiso de garbanzos con ternera, zanahoria y pimiento", "Zanahoria", 70),
            AuxRecipeStore("Guiso de pavo al ajillo", "Aceite de oliva", 5),
            AuxRecipeStore("Guiso de pavo al ajillo", "Ajo", 20),
            AuxRecipeStore("Guiso de pavo al ajillo", "Caldo de pollo (Gallina Blanca)", 80),
            AuxRecipeStore("Guiso de pavo al ajillo", "Champiñón", 120),
            AuxRecipeStore("Guiso de pavo al ajillo", "Leche desnatada sin lactosa (Pascual)", 20),
            AuxRecipeStore("Guiso de pavo al ajillo", "Pechuga de pavo", 150),
            AuxRecipeStore("Guiso de pavo al ajillo", "Pimienta negra", 0),
            AuxRecipeStore("Guiso de pavo al ajillo", "Sal de mesa", 0),
            AuxRecipeStore("Guiso de pavo al ajillo", "Tomillo", 0),
            AuxRecipeStore("Guiso de ternera, patata y cebolla", "Aceite de oliva", 12),
            AuxRecipeStore("Guiso de ternera, patata y cebolla", "Azafrán", 0),
            AuxRecipeStore("Guiso de ternera, patata y cebolla", "Cebolla", 95),
            AuxRecipeStore("Guiso de ternera, patata y cebolla", "Pastilla caldo de carne", 6),
            AuxRecipeStore("Guiso de ternera, patata y cebolla", "Patata", 180),
            AuxRecipeStore("Guiso de ternera, patata y cebolla", "Sal de mesa", 0),
            AuxRecipeStore("Guiso de ternera, patata y cebolla", "Ternera", 140),
            AuxRecipeStore("Guiso de ternera, patata y cebolla", "Vino blanco (cocinar)", 60),
            AuxRecipeStore("Guiso de ternera, patata y cebolla", "Zanahoria", 85),
            AuxRecipeStore("Hamburguesa de pollo y ensalada", "Aceite de oliva", 5),
            AuxRecipeStore("Hamburguesa de pollo y ensalada", "Cebolla", 40),
            AuxRecipeStore("Hamburguesa de pollo y ensalada", "Hamburguesa pollo", 120),
            AuxRecipeStore("Hamburguesa de pollo y ensalada", "Hoja verde", 35),
            AuxRecipeStore("Hamburguesa de pollo y ensalada", "Pan thins", 40),
            AuxRecipeStore("Hamburguesa de pollo y ensalada", "Queso untar light", 30),
            AuxRecipeStore("Hamburguesa de pollo y ensalada", "Sal de mesa", 0),
            AuxRecipeStore("Hamburguesa de pollo y ensalada", "Tomate", 80),
            AuxRecipeStore("Hamburguesa de ternera con queso, tomate, espinacas y cebolla", "Cebolla", 30),
            AuxRecipeStore(
                "Hamburguesa de ternera con queso, tomate, espinacas y cebolla",
                "Espinacas normales o baby (Hacendado)",
                10
            ),
            AuxRecipeStore("Hamburguesa de ternera con queso, tomate, espinacas y cebolla", "Hamburguesa ternera", 110),
            AuxRecipeStore("Hamburguesa de ternera con queso, tomate, espinacas y cebolla", "Pan hamburguesa", 50),
            AuxRecipeStore(
                "Hamburguesa de ternera con queso, tomate, espinacas y cebolla",
                "Queso manchego semicurado",
                15
            ),
            AuxRecipeStore("Hamburguesa de ternera con queso, tomate, espinacas y cebolla", "Tomate", 40),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Aceite de oliva", 10),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Agua", 400),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Calabacín", 100),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Canela", 0),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Cebolla", 50),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Curry", 0),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Lentejas cocidas", 150),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Manzana", 60),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Pechuga de pollo", 100),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Puerro", 50),
            AuxRecipeStore("Lentejas con pollo, calabacín, manzana, curry, canela", "Zanahoria", 60),
            AuxRecipeStore("Merluza al horno con cebolla, tomate y piñones", "Aceite de oliva", 5),
            AuxRecipeStore("Merluza al horno con cebolla, tomate y piñones", "Ajo", 5),
            AuxRecipeStore("Merluza al horno con cebolla, tomate y piñones", "Cebolla", 50),
            AuxRecipeStore("Merluza al horno con cebolla, tomate y piñones", "Hierbas provenzales", 0),
            AuxRecipeStore("Merluza al horno con cebolla, tomate y piñones", "Merluza congelada", 250),
            AuxRecipeStore("Merluza al horno con cebolla, tomate y piñones", "Perejil", 0),
            AuxRecipeStore("Merluza al horno con cebolla, tomate y piñones", "Piñones", 5),
            AuxRecipeStore("Merluza al horno con cebolla, tomate y piñones", "Sal de mesa", 0),
            AuxRecipeStore("Merluza al horno con cebolla, tomate y piñones", "Tomate triturado (Hacendado)", 150),
            AuxRecipeStore("Pechuga de pollo con patata y tomate abierto con albahaca", "Aceite de oliva", 5),
            AuxRecipeStore("Pechuga de pollo con patata y tomate abierto con albahaca", "Agua", 10),
            AuxRecipeStore("Pechuga de pollo con patata y tomate abierto con albahaca", "Albahaca fresca", 0),
            AuxRecipeStore("Pechuga de pollo con patata y tomate abierto con albahaca", "Patata", 200),
            AuxRecipeStore("Pechuga de pollo con patata y tomate abierto con albahaca", "Pechuga de pollo", 200),
            AuxRecipeStore("Pechuga de pollo con patata y tomate abierto con albahaca", "Pimentón", 0),
            AuxRecipeStore("Pechuga de pollo con patata y tomate abierto con albahaca", "Tomate", 200),
            AuxRecipeStore("Pechuga de pollo con pimiento y cebolla a la plancha", "Aceite de oliva", 6),
            AuxRecipeStore("Pechuga de pollo con pimiento y cebolla a la plancha", "Cebolla", 80),
            AuxRecipeStore("Pechuga de pollo con pimiento y cebolla a la plancha", "Pechuga de pollo", 200),
            AuxRecipeStore("Pechuga de pollo con pimiento y cebolla a la plancha", "Pimienta negra", 0),
            AuxRecipeStore("Pechuga de pollo con pimiento y cebolla a la plancha", "Pimiento fresco o congelado", 240),
            AuxRecipeStore("Pechuga de pollo con pimiento y cebolla a la plancha", "Sal de mesa", 0),
            AuxRecipeStore("Pizza de jamón serrano, rúcula, tomates cherry y queso parmesano", "Cherry", 50),
            AuxRecipeStore("Pizza de jamón serrano, rúcula, tomates cherry y queso parmesano", "Jamón ibérico", 30),
            AuxRecipeStore("Pizza de jamón serrano, rúcula, tomates cherry y queso parmesano", "Masa de pizza", 130),
            AuxRecipeStore("Pizza de jamón serrano, rúcula, tomates cherry y queso parmesano", "Queso parmesano", 20),
            AuxRecipeStore(
                "Pizza de jamón serrano, rúcula, tomates cherry y queso parmesano",
                "Rúcula (Hacendado)",
                10
            ),
            AuxRecipeStore(
                "Pizza de jamón serrano, rúcula, tomates cherry y queso parmesano",
                "Tomate triturado (Hacendado)",
                80
            ),
            AuxRecipeStore("Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla", "Aguacate", 30),
            AuxRecipeStore(
                "Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla",
                "Arroz basmati",
                35
            ),
            AuxRecipeStore("Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla", "Atún fresco", 100),
            AuxRecipeStore("Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla", "Cebolla", 40),
            AuxRecipeStore("Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla", "Cilantro", 5),
            AuxRecipeStore("Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla", "Edamame", 50),
            AuxRecipeStore("Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla", "Mango", 75),
            AuxRecipeStore("Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla", "Sal de mesa", 1),
            AuxRecipeStore(
                "Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla",
                "Salsa de soja (Hacendado)",
                20
            ),
            AuxRecipeStore(
                "Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla",
                "Semillas de sésamo",
                5
            ),
            AuxRecipeStore("Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla", "Zanahoria", 50),
            AuxRecipeStore("Poke bowl de arroz blanco, edamame, bonito, mango, aguacate y cebolla", "Zumo de lima", 10),
            AuxRecipeStore("Pollo al horno con zumo de naranja y pimiento", "Aceite de oliva", 5),
            AuxRecipeStore("Pollo al horno con zumo de naranja y pimiento", "Laurel", 0),
            AuxRecipeStore("Pollo al horno con zumo de naranja y pimiento", "Muslo de pollo", 220),
            AuxRecipeStore("Pollo al horno con zumo de naranja y pimiento", "Perejil", 0),
            AuxRecipeStore("Pollo al horno con zumo de naranja y pimiento", "Pimienta negra", 0),
            AuxRecipeStore("Pollo al horno con zumo de naranja y pimiento", "Pimiento fresco o congelado", 150),
            AuxRecipeStore("Pollo al horno con zumo de naranja y pimiento", "Sal de mesa", 0),
            AuxRecipeStore("Pollo al horno con zumo de naranja y pimiento", "Zumo de naranja", 20),
            AuxRecipeStore("Porridge de copos de avena con cacao", "Cacao en polvo 0%", 10),
            AuxRecipeStore("Porridge de copos de avena con cacao", "Copos avena", 35),
            AuxRecipeStore("Porridge de copos de avena con cacao", "Leche desnatada sin lactosa (Pascual)", 150),
            AuxRecipeStore("Quiche casera de brócoli, puerro y queso feta", "Aceite de oliva", 5),
            AuxRecipeStore("Quiche casera de brócoli, puerro y queso feta", "Agua", 15),
            AuxRecipeStore("Quiche casera de brócoli, puerro y queso feta", "Brócoli fresco o congelado", 70),
            AuxRecipeStore("Quiche casera de brócoli, puerro y queso feta", "Harina de trigo", 35),
            AuxRecipeStore("Quiche casera de brócoli, puerro y queso feta", "Huevo crudo entero", 60),
            AuxRecipeStore(
                "Quiche casera de brócoli, puerro y queso feta",
                "Leche desnatada sin lactosa (Pascual)",
                75
            ),
            AuxRecipeStore("Quiche casera de brócoli, puerro y queso feta", "Pimienta negra", 2),
            AuxRecipeStore("Quiche casera de brócoli, puerro y queso feta", "Puerro", 60),
            AuxRecipeStore("Quiche casera de brócoli, puerro y queso feta", "Queso feta", 30),
            AuxRecipeStore("Quiche casera de brócoli, puerro y queso feta", "Sal de mesa", 2),
            AuxRecipeStore("Revuelto de alcachofas y atún", "Aceite de oliva", 5),
            AuxRecipeStore("Revuelto de alcachofas y atún", "Alcachofa congelada baby", 300),
            AuxRecipeStore("Revuelto de alcachofas y atún", "Atún en aceite oli lata", 60),
            AuxRecipeStore("Revuelto de alcachofas y atún", "Huevo crudo entero", 60),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Aceite de oliva", 5),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Brócoli fresco o congelado", 80),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Cebolla", 80),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Cebollino", 0),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Jengibre", 0),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Miel", 5),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Pimienta negra", 0),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Sal de mesa", 0),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Salmón fresco", 150),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Salsa de soja (Hacendado)", 10),
            AuxRecipeStore("Salmón con brócoli, zanahoria y cebolla a la plancha", "Zanahoria", 80),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Aceite de oliva", 5),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Brócoli fresco o congelado", 100),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Cebolla", 100),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Cebollino", 0),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Jengibre", 0),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Miel", 5),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Pimienta negra", 0),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Sal de mesa", 0),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Salmón fresco", 200),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Salsa de soja (Hacendado)", 10),
            AuxRecipeStore("Salmón y salteado de brócoli, zanahoria y cebolla", "Zanahoria", 100),
            AuxRecipeStore("Revuelto de huevo, patata, calabacín y cebolla", "Calabacín", 200),
            AuxRecipeStore("Revuelto de huevo, patata, calabacín y cebolla", "Cebolla", 80),
            AuxRecipeStore("Revuelto de huevo, patata, calabacín y cebolla", "Huevo crudo entero", 90),
            AuxRecipeStore("Revuelto de huevo, patata, calabacín y cebolla", "Patata", 100),
            AuxRecipeStore("Revuelto de huevo, patata, calabacín y cebolla", "Aceite de oliva", 5),
            AuxRecipeStore("Salteado de soja texturizada, guisantes, cebolla y pimiento", "Aceite de oliva", 5),
            AuxRecipeStore("Salteado de soja texturizada, guisantes, cebolla y pimiento", "Ajo", 0),
            AuxRecipeStore("Salteado de soja texturizada, guisantes, cebolla y pimiento", "Cebolla", 80),
            AuxRecipeStore(
                "Salteado de soja texturizada, guisantes, cebolla y pimiento",
                "Guisantes congelados (Hacendado)",
                80
            ),
            AuxRecipeStore("Salteado de soja texturizada, guisantes, cebolla y pimiento", "Pimienta negra", 0),
            AuxRecipeStore(
                "Salteado de soja texturizada, guisantes, cebolla y pimiento",
                "Pimiento fresco o congelado",
                80
            ),
            AuxRecipeStore("Salteado de soja texturizada, guisantes, cebolla y pimiento", "Sal de mesa", 0),
            AuxRecipeStore("Salteado de soja texturizada, guisantes, cebolla y pimiento", "Soja texturizada", 90),
            AuxRecipeStore("Salteado de soja texturizada, guisantes, cebolla y pimiento", "Tomillo", 0),
            AuxRecipeStore("Solomillo de ternera con patatas, calabacín y pimiento", "Aceite de oliva", 5),
            AuxRecipeStore("Solomillo de ternera con patatas, calabacín y pimiento", "Calabacín", 150),
            AuxRecipeStore("Solomillo de ternera con patatas, calabacín y pimiento", "Patata", 200),
            AuxRecipeStore(
                "Solomillo de ternera con patatas, calabacín y pimiento",
                "Pimiento fresco o congelado",
                150
            ),
            AuxRecipeStore("Solomillo de ternera con patatas, calabacín y pimiento", "Ternera", 200),
            AuxRecipeStore("Sopa de verduras y pechuga de pollo", "Aceite de oliva", 5),
            AuxRecipeStore("Sopa de verduras y pechuga de pollo", "Caldo de verdura", 500),
            AuxRecipeStore("Sopa de verduras y pechuga de pollo", "Cebolla", 120),
            AuxRecipeStore("Sopa de verduras y pechuga de pollo", "Guisantes congelados (Hacendado)", 120),
            AuxRecipeStore("Sopa de verduras y pechuga de pollo", "Nabo", 90),
            AuxRecipeStore("Sopa de verduras y pechuga de pollo", "Pechuga de pollo", 300),
            AuxRecipeStore("Sopa de verduras y pechuga de pollo", "Perejil", 0),
            AuxRecipeStore("Sopa de verduras y pechuga de pollo", "Zanahoria", 150),
            AuxRecipeStore("Tartar de salmón y tomate", "Cebolleta", 25),
            AuxRecipeStore("Tartar de salmón y tomate", "Pimienta negra", 0),
            AuxRecipeStore("Tartar de salmón y tomate", "Salmón fresco", 180),
            AuxRecipeStore("Tartar de salmón y tomate", "Salsa de soja (Hacendado)", 10),
            AuxRecipeStore("Tartar de salmón y tomate", "Salsa Lea Perrins", 12),
            AuxRecipeStore("Tartar de salmón y tomate", "Semillas de sésamo", 3),
            AuxRecipeStore("Tartar de salmón y tomate", "Tomate", 180),
            AuxRecipeStore("Tartar de salmón y tomate", "Zumo de lima", 25),
            AuxRecipeStore("Tataki de atún con ensalada de aguacate y tomate", "Aceite de oliva", 2),
            AuxRecipeStore("Tataki de atún con ensalada de aguacate y tomate", "Aguacate", 30),
            AuxRecipeStore("Tataki de atún con ensalada de aguacate y tomate", "Atún congelado (Hacendado)", 140),
            AuxRecipeStore("Tataki de atún con ensalada de aguacate y tomate", "Jengibre", 2),
            AuxRecipeStore("Tataki de atún con ensalada de aguacate y tomate", "Lechuga iceberg", 50),
            AuxRecipeStore("Tataki de atún con ensalada de aguacate y tomate", "Sal de mesa", 0),
            AuxRecipeStore("Tataki de atún con ensalada de aguacate y tomate", "Salsa de soja (Hacendado)", 10),
            AuxRecipeStore("Tataki de atún con ensalada de aguacate y tomate", "Semillas de sésamo", 5),
            AuxRecipeStore("Tataki de atún con ensalada de aguacate y tomate", "Tomate", 120),
            AuxRecipeStore("Tataki de atún con ensalada de aguacate y tomate", "Vinagre módena", 0),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Aceite de oliva", 5),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Ajo", 1),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Arroz estándar", 40),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Cebolleta", 30),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Maicena", 3),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Miel", 5),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Perejil", 1),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Pimiento fresco o congelado", 40),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Sal de mesa", 1),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Salsa de soja (Hacendado)", 20),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Ternera", 100),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Vinagre de vino blanco", 5),
            AuxRecipeStore("Ternera picante / Arroz blanco", "Zanahoria", 40),
            AuxRecipeStore("Tortilla de atún y berenjena a la plancha", "Aceite de oliva", 5),
            AuxRecipeStore("Tortilla de atún y berenjena a la plancha", "Atún en aceite oli lata", 60),
            AuxRecipeStore("Tortilla de atún y berenjena a la plancha", "Berenjena", 350),
            AuxRecipeStore("Tortilla de atún y berenjena a la plancha", "Huevo crudo entero", 60),
            AuxRecipeStore("Tortilla de atún y berenjena a la plancha", "Sal de mesa", 0),
            AuxRecipeStore("Tortilla de huevo y claras con espinacas y queso feta", "Aceite de oliva", 5),
            AuxRecipeStore("Tortilla de huevo y claras con espinacas y queso feta", "Cebollino", 0),
            AuxRecipeStore("Tortilla de huevo y claras con espinacas y queso feta", "Clara de huevo (Hacendado)", 200),
            AuxRecipeStore(
                "Tortilla de huevo y claras con espinacas y queso feta",
                "Espinacas normales o baby (Hacendado)",
                80
            ),
            AuxRecipeStore("Tortilla de huevo y claras con espinacas y queso feta", "Huevo crudo entero", 60),
            AuxRecipeStore("Tortilla de huevo y claras con espinacas y queso feta", "Pimienta negra", 0),
            AuxRecipeStore("Tortilla de huevo y claras con espinacas y queso feta", "Queso feta", 30),
            AuxRecipeStore("Tortilla de huevo y claras con espinacas y queso feta", "Sal de mesa", 0),
            AuxRecipeStore("Tortilla de jamón york y ajos tiernos y ensalada de queso y tomate", "Ajos tiernos", 60),
            AuxRecipeStore(
                "Tortilla de jamón york y ajos tiernos y ensalada de queso y tomate",
                "Clara de huevo (Hacendado)",
                150
            ),
            AuxRecipeStore("Tortilla de jamón york y ajos tiernos y ensalada de queso y tomate", "Hoja verde", 40),
            AuxRecipeStore(
                "Tortilla de jamón york y ajos tiernos y ensalada de queso y tomate",
                "Huevo crudo entero",
                120
            ),
            AuxRecipeStore("Tortilla de jamón york y ajos tiernos y ensalada de queso y tomate", "Jamón York", 60),
            AuxRecipeStore("Tortilla de jamón york y ajos tiernos y ensalada de queso y tomate", "Perejil", 0),
            AuxRecipeStore(
                "Tortilla de jamón york y ajos tiernos y ensalada de queso y tomate",
                "Queso fresco 0MG (Burgo de Arias)",
                120
            ),
            AuxRecipeStore("Tortilla de jamón york y ajos tiernos y ensalada de queso y tomate", "Sal de mesa", 0),
            AuxRecipeStore("Tortilla de jamón york y ajos tiernos y ensalada de queso y tomate", "Tomate", 200),
            AuxRecipeStore("Tostada de jamón serrano con tomate", "Aceite de oliva", 5),
            AuxRecipeStore("Tostada de jamón serrano con tomate", "Jamón ibérico", 30),
            AuxRecipeStore("Tostada de jamón serrano con tomate", "Pan blanco", 60),
            AuxRecipeStore("Tostada de jamón serrano con tomate", "Sal de mesa", 1),
            AuxRecipeStore("Tostada de jamón serrano con tomate", "Tomate", 50),
            AuxRecipeStore("Tostada de jamón serrano con tomate y café con leche", "Aceite de oliva", 5),
            AuxRecipeStore("Tostada de jamón serrano con tomate y café con leche", "Jamón ibérico", 30),
            AuxRecipeStore(
                "Tostada de jamón serrano con tomate y café con leche",
                "Leche desnatada sin lactosa (Pascual)",
                130
            ),
            AuxRecipeStore("Tostada de jamón serrano con tomate y café con leche", "Pan blanco", 60),
            AuxRecipeStore("Tostada de jamón serrano con tomate y café con leche", "Sal de mesa", 1),
            AuxRecipeStore("Tostada de jamón serrano con tomate y café con leche", "Tomate", 50),
            AuxRecipeStore("Tostada de lomo, queso de untar y tomate", "Lomo embuchado", 20),
            AuxRecipeStore("Tostada de lomo, queso de untar y tomate", "Pan integral", 60),
            AuxRecipeStore("Tostada de lomo, queso de untar y tomate", "Queso untar light", 15),
            AuxRecipeStore("Tostada de lomo, queso de untar y tomate", "Tomate", 80),
            AuxRecipeStore(
                "Tostada de lomo, queso de untar y tomate y café con leche",
                "Leche desnatada sin lactosa (Pascual)",
                130
            ),
            AuxRecipeStore("Tostada de lomo, queso de untar y tomate y café con leche", "Lomo embuchado", 20),
            AuxRecipeStore("Tostada de lomo, queso de untar y tomate y café con leche", "Pan integral", 60),
            AuxRecipeStore("Tostada de lomo, queso de untar y tomate y café con leche", "Queso untar light", 15),
            AuxRecipeStore("Tostada de lomo, queso de untar y tomate y café con leche", "Tomate", 80),
            AuxRecipeStore("Un par de mandarinas", "Mandarina", 120),
            AuxRecipeStore("Un yogur de proteína (Arándanos)", "Yogur proteinas arándanos (Hacendado)", 120),
            AuxRecipeStore("Un yogur de proteína (Chocolate)", "Yogur proteinas chocolate (Hacendado)", 120),
            AuxRecipeStore("Un yogur de proteína y una pera", "Pera", 150),
            AuxRecipeStore("Un yogur de proteína y una pera", "Yogur proteinas mango (Hacendado)", 120),
            AuxRecipeStore(
                "Un yogur de proteína, una pera y una onza de chocolate",
                "Chocolate negro 85% (Hacendado)",
                10
            ),
            AuxRecipeStore("Un yogur de proteína, una pera y una onza de chocolate", "Pera", 150),
            AuxRecipeStore(
                "Un yogur de proteína, una pera y una onza de chocolate",
                "Yogur proteinas mango (Hacendado)",
                120
            ),
            AuxRecipeStore("Una pera", "Pera", 150),
            AuxRecipeStore("Wrap de pollo, aguacate y rúcula", "Aguacate", 30),
            AuxRecipeStore("Wrap de pollo, aguacate y rúcula", "Pechuga de pollo", 140),
            AuxRecipeStore("Wrap de pollo, aguacate y rúcula", "Rúcula (Hacendado)", 20),
            AuxRecipeStore("Wrap de pollo, aguacate y rúcula", "Sal de mesa", 0),
            AuxRecipeStore("Wrap de pollo, aguacate y rúcula", "Tortita de trigo (Hacendado)", 35),
            AuxRecipeStore("Yogur con onza de chocolate", "Chocolate negro 72% (Hacendado)", 10),
            AuxRecipeStore("Yogur con onza de chocolate", "Yogur cremoso natural edulcorado 0MG (Hacendado)", 125),
            AuxRecipeStore("Yogur desnatado con muesli", "Muesli crunchy 0 azucar añadido (Hacendado)", 20),
            AuxRecipeStore("Yogur desnatado con muesli", "Yogur cremoso natural edulcorado 0MG (Hacendado)", 125),
            AuxRecipeStore(
                "Porridge de copos de avena con plátano y proteína",
                "Leche desnatada sin lactosa (Pascual)",
                200
            ),
            AuxRecipeStore("Porridge de copos de avena con plátano y proteína", "Copos avena", 40),
            AuxRecipeStore("Porridge de copos de avena con plátano y proteína", "Plátano", 45),
            AuxRecipeStore("Porridge de copos de avena con plátano y proteína", "Proteína polvo", 20),
            AuxRecipeStore("Leche desnatada con muesli y proteína", "Leche desnatada sin lactosa (Pascual)", 250),
            AuxRecipeStore("Leche desnatada con muesli y proteína", "Muesli crunchy 0 azucar añadido (Hacendado)", 40),
            AuxRecipeStore("Leche desnatada con muesli y proteína", "Proteína polvo", 20),
            AuxRecipeStore("Pizza de pollo, calabacín y queso ", "Masa de pizza", 120),
            AuxRecipeStore("Pizza de pollo, calabacín y queso ", "Tomate triturado (Hacendado)", 80),
            AuxRecipeStore("Pizza de pollo, calabacín y queso ", "Calabacín", 50),
            AuxRecipeStore("Pizza de pollo, calabacín y queso ", "Queso manchego semicurado", 20),
            AuxRecipeStore("Pizza de pollo, calabacín y queso ", "Pechuga de pollo fiambre", 70),
            AuxRecipeStore("Poke bowl de arroz blanco, bonito, mango, maíz, cebolla y pepino", "Arroz basmati", 70),
            AuxRecipeStore("Poke bowl de arroz blanco, bonito, mango, maíz, cebolla y pepino", "Atún fresco", 150),
            AuxRecipeStore("Poke bowl de arroz blanco, bonito, mango, maíz, cebolla y pepino", "Mango", 40),
            AuxRecipeStore("Poke bowl de arroz blanco, bonito, mango, maíz, cebolla y pepino", "Zanahoria", 40),
            AuxRecipeStore("Poke bowl de arroz blanco, bonito, mango, maíz, cebolla y pepino", "Zumo de lima", 20),
            AuxRecipeStore("Poke bowl de arroz blanco, bonito, mango, maíz, cebolla y pepino", "Cilantro", 5),
            AuxRecipeStore(
                "Poke bowl de arroz blanco, bonito, mango, maíz, cebolla y pepino",
                "Salsa de soja (Hacendado)",
                20
            ),
            AuxRecipeStore("Poke bowl de arroz blanco, bonito, mango, maíz, cebolla y pepino", "Semillas de sésamo", 2),
            AuxRecipeStore(
                "Poke bowl de arroz blanco, bonito, mango, maíz, cebolla y pepino",
                "Maíz dulce (Hacendado)",
                30
            ),
            AuxRecipeStore("Poke bowl de arroz blanco, bonito, mango, maíz, cebolla y pepino", "Cebolla", 50),
            AuxRecipeStore(
                "Hamburguesa de ternera con queso de cabra, manzana y espinacas",
                "Hamburguesa ternera",
                120
            ),
            AuxRecipeStore("Hamburguesa de ternera con queso de cabra, manzana y espinacas", "Patatas cong horno", 100),
            AuxRecipeStore("Hamburguesa de ternera con queso de cabra, manzana y espinacas", "Manzana", 60),
            AuxRecipeStore("Hamburguesa de ternera con queso de cabra, manzana y espinacas", "Queso de cabra", 40),
            AuxRecipeStore(
                "Hamburguesa de ternera con queso de cabra, manzana y espinacas",
                "Espinacas normales o baby (Hacendado)",
                15
            ),
            AuxRecipeStore("Hamburguesa de ternera con queso de cabra, manzana y espinacas", "Pan hamburguesa", 1),
            AuxRecipeStore("Pasta con pechuga de pollo, queso y champiñones", "Pasta (hélices)", 70),
            AuxRecipeStore("Pasta con pechuga de pollo, queso y champiñones", "Pechuga de pollo", 120),
            AuxRecipeStore("Pasta con pechuga de pollo, queso y champiñones", "Champiñón", 100),
            AuxRecipeStore("Pasta con pechuga de pollo, queso y champiñones", "Queso manchego semicurado", 20),
            AuxRecipeStore("Pasta con pechuga de pollo, queso y champiñones", "Aceite de oliva", 5),
            AuxRecipeStore("Pasta con pechuga de pollo, queso y champiñones", "Sal de mesa", 1),
            AuxRecipeStore("Pasta con pechuga de pollo, queso y champiñones", "Pimienta negra", 1),
            AuxRecipeStore(
                "Smoothie bowl de frambuesas y fresas con copos de avena, chía y proteína",
                "Frutos rojos congelados",
                150
            ),
            AuxRecipeStore(
                "Smoothie bowl de frambuesas y fresas con copos de avena, chía y proteína",
                "Leche desnatada sin lactosa (Pascual)",
                200
            ),
            AuxRecipeStore(
                "Smoothie bowl de frambuesas y fresas con copos de avena, chía y proteína",
                "Semillas de chía",
                15
            ),
            AuxRecipeStore(
                "Smoothie bowl de frambuesas y fresas con copos de avena, chía y proteína",
                "Copos avena",
                20
            ),
            AuxRecipeStore(
                "Smoothie bowl de frambuesas y fresas con copos de avena, chía y proteína",
                "Muesli crunchy 0 azucar añadido (Hacendado)",
                30
            ),
            AuxRecipeStore(
                "Smoothie bowl de frambuesas y fresas con copos de avena, chía y proteína",
                "Proteína polvo",
                20
            ),
            AuxRecipeStore(
                "Queso batido con avena, arándanos y proteína / plátano / gelatina proteína",
                "Yogur proteinas blanco (Hacendado)",
                250
            ),
            AuxRecipeStore(
                "Queso batido con avena, arándanos y proteína / plátano / gelatina proteína",
                "Arándanos",
                50
            ),
            AuxRecipeStore(
                "Queso batido con avena, arándanos y proteína / plátano / gelatina proteína",
                "Copos avena",
                20
            ),
            AuxRecipeStore("Queso batido con avena, arándanos y proteína / plátano / gelatina proteína", "Plátano", 90),
            AuxRecipeStore(
                "Queso batido con avena, arándanos y proteína / plátano / gelatina proteína",
                "Gelatina de proteína",
                100
            ),
            AuxRecipeStore(
                "Ensalada de brotes verdes, cherry, maíz, atún, parmesano, cebolla y huevo",
                "Hoja verde",
                80
            ),
            AuxRecipeStore("Ensalada de brotes verdes, cherry, maíz, atún, parmesano, cebolla y huevo", "Cherry", 30),
            AuxRecipeStore(
                "Ensalada de brotes verdes, cherry, maíz, atún, parmesano, cebolla y huevo",
                "Maíz dulce (Hacendado)",
                35
            ),
            AuxRecipeStore(
                "Ensalada de brotes verdes, cherry, maíz, atún, parmesano, cebolla y huevo",
                "Atún en aceite oli lata",
                60
            ),
            AuxRecipeStore(
                "Ensalada de brotes verdes, cherry, maíz, atún, parmesano, cebolla y huevo",
                "Queso parmesano",
                30
            ),
            AuxRecipeStore("Ensalada de brotes verdes, cherry, maíz, atún, parmesano, cebolla y huevo", "Cebolla", 50),
            AuxRecipeStore(
                "Ensalada de brotes verdes, cherry, maíz, atún, parmesano, cebolla y huevo",
                "Huevo crudo entero",
                60
            ),
            AuxRecipeStore("Ensaladilla rusa", "Atún en aceite oli lata", 60),
            AuxRecipeStore("Ensaladilla rusa", "Huevo crudo entero", 60),
            AuxRecipeStore("Ensaladilla rusa", "Mayonesa", 30),
            AuxRecipeStore("Ensaladilla rusa", "Arreglo ensaladilla", 250),
            AuxRecipeStore("Tostada de jamón serrano", "Aceite de oliva", 5),
            AuxRecipeStore("Tostada de jamón serrano", "Jamón ibérico", 30),
            AuxRecipeStore("Tostada de jamón serrano", "Pan blanco", 60),
            AuxRecipeStore("Tostada de jamón serrano", "Sal de mesa", 1),
            AuxRecipeStore("Tostada de jamón serrano y café con leche", "Aceite de oliva", 5),
            AuxRecipeStore("Tostada de jamón serrano y café con leche", "Jamón ibérico", 30),
            AuxRecipeStore("Tostada de jamón serrano y café con leche", "Leche desnatada sin lactosa (Pascual)", 130),
            AuxRecipeStore("Tostada de jamón serrano y café con leche", "Pan blanco", 60),
            AuxRecipeStore("Tostada de jamón serrano y café con leche", "Sal de mesa", 1),
            AuxRecipeStore("Tostada de huevos revueltos y salmón ahumado", "Huevo crudo entero", 60),
            AuxRecipeStore("Tostada de huevos revueltos y salmón ahumado", "Pan blanco", 40),
            AuxRecipeStore("Tostada de huevos revueltos y salmón ahumado", "Salmón ahumado", 30),
            AuxRecipeStore("Tostada de huevos revueltos y salmón ahumado", "Sal de mesa", 1),
            AuxRecipeStore("Tostada de huevos revueltos y salmón ahumado", "Pimienta negra", 1),
            AuxRecipeStore("Tartar de salmón y aguacate", "Salmón fresco", 160),
            AuxRecipeStore("Tartar de salmón y aguacate", "Aguacate", 80),
            AuxRecipeStore("Tartar de salmón y aguacate", "Cebolleta", 25),
            AuxRecipeStore("Tartar de salmón y aguacate", "Zumo de lima", 25),
            AuxRecipeStore("Tartar de salmón y aguacate", "Salsa de soja (Hacendado)", 20),
            AuxRecipeStore("Tartar de salmón y aguacate", "Salsa Lea Perrins", 10),
            AuxRecipeStore("Tartar de salmón y aguacate", "Mostaza", 2),
            AuxRecipeStore("Tartar de salmón y aguacate", "Pimienta negra", 1),
            AuxRecipeStore("Café con leche y plátano", "Leche desnatada sin lactosa (Pascual)", 250),
            AuxRecipeStore("Café con leche y plátano", "Plátano", 90),
            AuxRecipeStore("Wrap de atún, aguacate, zanahoria, pepino y vinagreta", "Tortita de trigo (Hacendado)", 70),
            AuxRecipeStore("Wrap de atún, aguacate, zanahoria, pepino y vinagreta", "Atún en aceite oli lata", 90),
            AuxRecipeStore("Wrap de atún, aguacate, zanahoria, pepino y vinagreta", "Zanahoria", 60),
            AuxRecipeStore("Wrap de atún, aguacate, zanahoria, pepino y vinagreta", "Aguacate", 40),
            AuxRecipeStore("Wrap de atún, aguacate, zanahoria, pepino y vinagreta", "Zumo de lima", 10),
            AuxRecipeStore("Wrap de atún, aguacate, zanahoria, pepino y vinagreta", "Pimienta negra", 1),
            AuxRecipeStore("Wrap de atún, aguacate, zanahoria, pepino y vinagreta", "Cilantro", 1),
            AuxRecipeStore("Pasta boloñesa de pollo", "Tomate triturado (Hacendado)", 150),
            AuxRecipeStore("Pasta boloñesa de pollo", "Pasta (hélices)", 50),
            AuxRecipeStore("Pasta boloñesa de pollo", "Pechuga de pollo", 120),
            AuxRecipeStore("Pasta boloñesa de pollo", "Zanahoria", 50),
            AuxRecipeStore("Pasta boloñesa de pollo", "Cebolla", 50),
            AuxRecipeStore("Pasta boloñesa de pollo", "Aceite de oliva", 7),
            AuxRecipeStore("Pasta boloñesa de pollo", "Ajo", 10),
            AuxRecipeStore("Tostada de pechuga de pavo, tomate y orégano", "Pechuga de pavo", 60),
            AuxRecipeStore("Tostada de pechuga de pavo, tomate y orégano", "Tomate", 60),
            AuxRecipeStore("Tostada de pechuga de pavo, tomate y orégano", "Pan blanco", 60),
            AuxRecipeStore("Tostada de pechuga de pavo, tomate y orégano", "Orégano", 2),
            AuxRecipeStore("Tostada de pechuga de pavo, tomate y orégano", "Aceite de oliva", 5),
            AuxRecipeStore("Poke preparado", "Poke preparado", 450),
            AuxRecipeStore("Guiso de garbanzos con pollo, huevo duro y zanahoria", "Caldo de verdura", 400),
            AuxRecipeStore(
                "Guiso de garbanzos con pollo, huevo duro y zanahoria",
                "Garbanzo cocido pedrosillano (Hacendado)",
                150
            ),
            AuxRecipeStore("Guiso de garbanzos con pollo, huevo duro y zanahoria", "Zanahoria", 100),
            AuxRecipeStore("Guiso de garbanzos con pollo, huevo duro y zanahoria", "Pechuga de pollo", 100),
            AuxRecipeStore("Guiso de garbanzos con pollo, huevo duro y zanahoria", "Cebolla", 80),
            AuxRecipeStore("Guiso de garbanzos con pollo, huevo duro y zanahoria", "Huevo crudo entero", 60),
            AuxRecipeStore("Guiso de garbanzos con pollo, huevo duro y zanahoria", "Aceite de oliva", 5),
            AuxRecipeStore("Guiso de garbanzos con pollo, huevo duro y zanahoria", "Ajo", 7),
            AuxRecipeStore("Salmón a la plancha / Patata al vapor y espárragos trigueros", "Salmón fresco", 120),
            AuxRecipeStore("Salmón a la plancha / Patata al vapor y espárragos trigueros", "Patata", 80),
            AuxRecipeStore("Salmón a la plancha / Patata al vapor y espárragos trigueros", "Espárragos trigueros", 100),
            AuxRecipeStore("Salmón a la plancha / Patata al vapor y espárragos trigueros", "Aceite de oliva", 5)
        )
    }

}


class AuxRecipeStore(
    val name: String,
    val productName: String,
    val grams: Int
)

fun ProductRepository.getByName(name: String): Product {

    return this.findByName(name).firstOrNull{it.name == name} ?: throw Error("Product not found")

}