package com.detarco.add_playground.ut02_sharedpreferences.repository

class UserRepository(
    private val memDataSource: MemDataSource<UserModel>,
    private val sharPrefDataSource: SharPrefDataSource<UserModel>
        ) {

}