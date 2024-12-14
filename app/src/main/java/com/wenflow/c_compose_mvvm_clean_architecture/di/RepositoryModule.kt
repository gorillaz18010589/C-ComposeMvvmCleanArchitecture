package com.wenflow.c_compose_mvvm_clean_architecture.di

import com.wenflow.c_compose_mvvm_clean_architecture.store.data.repository.ProductRepositoryImpl
import com.wenflow.c_compose_mvvm_clean_architecture.store.domain.repositroy.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 24.
 * ## 註解解釋：Dagger Hilt 中的 @Module、@InstallIn 和 @Binds
 *
 * 這段程式碼主要利用 Dagger Hilt 的機制來管理依賴注入，讓我們更清楚地了解整個應用程式的組成和物件之間的關係。
 *
 * ### @Module
 * * **作用：** 標示這個類別為一個 Module，表示這個類別內包含了用來提供物件的方法。
 * * **抽象類別 Module：** 在 Hilt 中，Module 可以是抽象類別，也可以是物件。使用抽象類別可以更方便地進行繼承和擴展。
 *
 * ### @InstallIn(SingletonComponent::class)
 * * **作用：** 指定這個 Module 所提供的物件 (在此例中是 `ProductRepository`) 應該被注入到哪個 Component 中。
 * * **SingletonComponent：** 意味著 `ProductRepository` 的實例會在整個應用程式生命週期中只建立一次，並被共享。
 *
 * ### @Binds
 * * **作用：** 將一個具體的實作類別綁定到一個介面或抽象類別。
 * * **abstract fun bindProductRepository(impl: ProductRepositoryImpl) : ProductRepository：**
 *     * `abstract`：表示這個方法是一個抽象方法，需要在子類中實現。
 *     * `bindProductRepository`：方法名稱通常用來表示綁定的關係。
 *     * `impl: ProductRepositoryImpl`：表示要綁定的具體實作類別。
 *     * `ProductRepository`：表示要綁定的介面或抽象類別。
 *
 * ### 整體解釋
 *
 * 這段程式碼的作用是：
 *
 * 1. **定義一個 Module：** `RepositoryModule` 這個 Module 是用來提供 `ProductRepository` 物件的。
 * 2. **綁定實作：** `@Binds` 註解將 `ProductRepositoryImpl` (具體實作類別) 綁定到 `ProductRepository` (介面或抽象類別)。這意味著，當其他類別需要 `ProductRepository` 時，實際上注入的是 `ProductRepositoryImpl` 的實例。
 * 3. **指定注入範圍：** `@InstallIn(SingletonComponent::class)` 這行表示，這個 `ProductRepository` 物件會被注入到整個應用程式的 SingletonComponent 中，也就是說，這個物件在整個應用程式生命週期中只會被建立一次，並且可以被任何需要這個物件的地方注入。
 *
 * ### 為什麼這樣做？
 *
 * * **解耦：** 將介面與實作分離，提高程式碼的可測試性、可維護性和擴展性。
 * * **依賴注入：** 將物件的建立和管理交給 Hilt 來處理，讓程式碼更乾淨、可維護。
 * * **單一實例：** 確保整個應用程式中只有一個 `ProductRepository` 的實例，避免重複建立，提高效率。
 *
 * ### 簡單來說
 *
 * 這段程式碼告訴 Hilt：
 *
 * * 我有一個 `RepositoryModule` 模組。
 * * 在這個模組裡，我提供了一個 `ProductRepository` 物件。
 * * 當需要 `ProductRepository` 時，實際上提供的是 `ProductRepositoryImpl` 的實例。
 * * 這個 `ProductRepository` 物件在整個應用程式中只建立一次，並且可以被任何需要這個物件的地方使用。
 *
 * **總結**
 *
 * 透過 `@Module`、`@InstallIn` 和 `@Binds` 這些註解，我們可以更清晰地定義物件的提供方式以及作用範圍，讓 Dagger Hilt 幫我們管理這些依賴關係，使得程式碼更易於理解和維護。
 *
 * **常見應用場景：**
 *
 * * **Repository Pattern：** 在 Android App 中，常使用 Repository Pattern 來隔離資料來源 (例如：網路、資料庫)。
 * * **依賴注入：** 將 Repository 注入到 ViewModel 或其他需要的地方，減少類別之間的耦合。
 * * **單元測試：** 可以方便地替換 `ProductRepositoryImpl` 的實作，進行單元測試。
 *
 * **補充**
 *
 * * **抽象類別 Module 的優勢：** 可以方便地繼承和擴展，例如，可以在子類中提供更多的綁定。
 * * **@Binds 的作用：** 不僅僅是綁定實作，還可以進行一些轉換或修飾。
 *
 *
 * */

@Module()
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(impl: ProductRepositoryImpl) : ProductRepository

}