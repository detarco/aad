package com.detarco.add_playground.ut03.ex06.data.remote

import com.detarco.add_playground.ut03.ex06.domain.BarModel
import com.detarco.add_playground.ut03.ex06.domain.TapaModel

class MockDataSource : RemoteDataSource {

    override fun getTapas(): Result<List<TapaModel>> {
        return Result.success(mutableListOf(
            TapaModel("1",
                "Bocadillo de Calamares",
                "Calamares, mahonesa, pan",
                1.5,
                "https://empty.jpg",
                BarModel("1", "Bar 01", "Calle 01")
            ),
            TapaModel("2",
                "Ensalad azul",
                "Queso azul, lechuga",
                1.5,
                "https://empty.jpg",
                BarModel("2", "Bar 02", "Calle 02")),
            TapaModel("3",
                "Sardinas divertidas",
                "Sardinas, pimientos, cebolla",
                1.5,
                "https://empty.jpg",
                BarModel("3", "Bar 03", "Calle 03"))
        ))
    }

    override fun getTapa(tapaId: String): Result<TapaModel> {
        return Result.success(
            TapaModel("1",
                "Bocadillo de Calamares",
                "Calamares, mahonesa, pan",
                1.5,
                "https://empty.jpg",
                BarModel("1", "Bar 01", "Calle 01")),
        )
    }
}