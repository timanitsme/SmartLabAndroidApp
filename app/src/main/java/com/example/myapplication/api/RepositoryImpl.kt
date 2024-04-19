package com.example.myapplication.api

import android.net.http.HttpException
import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.annotation.RequiresExtension
import com.example.myapplication.api.Api
import com.example.myapplication.model.RickAndMorty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RepositoryImpl(
    private val api: Api //Переменная интерфейса Api
) : Repository {

    //Переопределение метода из interface Repository. Здесь происходит реализация обработки данных
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getCharacter(): Flow<Result<RickAndMorty>> {
        return flow {
            val rickAndMortyFromApi = try {
                api.getCharacter()
            } catch (e: IOException) {
                e.printStackTrace()
                emit( Result.Error(message = "Error loading products"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            }

            emit(Result.Success(rickAndMortyFromApi))
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun sendCodeEmail(email: String): Flow<Result<String>> {
        return flow{
            val request = try{
                api.sendCodeEmail(email)
            }
            catch (e: IOException) {
                e.printStackTrace()
                emit( Result.Error(message = "Don't send code"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Don't send code"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Don't send code"))
                return@flow
            }
            emit(Result.Success(request))
        }
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun signIn(email: String, code: String): Flow<Result<String>> {
        return flow{
            val request = try{
                api.signIn(email, code)
            }
            catch (e: IOException) {
                e.printStackTrace()
                emit( Result.Error(message = "Don't send code"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Don't send code"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Don't send code"))
                return@flow
            }
            emit(Result.Success(request))
        }
    }
}