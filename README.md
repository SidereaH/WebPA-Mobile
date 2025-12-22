
# 📱 Webpa Mobile — Android приложение

Android-клиент для сервиса Webpa: поиск товаров, избранное, авторизация и профиль пользователя.

Приложение реализовано с использованием **Jetpack Compose**, **Clean Architecture** и **Single Source of Truth**.

---

## ✨ Возможности

* 🔍 Поиск товаров
* ⭐ Добавление / удаление из избранного (Room)
* 👤 Регистрация и авторизация пользователя
* 🔐 JWT-сессия (access / refresh token)
* 🙍‍♂️ Профиль пользователя
* 🧭 Навигация с защитой авторизованных экранов
* ⚡ Реактивный UI на StateFlow

---

## 🎥 Демонстрация работы

https://github.com/user-attachments/assets/a0e1a6b4-4797-48d2-8c8b-1fb8fc47560f

---

## 🧱 Архитектура

Проект построен по принципам **Clean Architecture**:

```
data/
 ├─ api        // Retrofit API
 ├─ dao        // Room DAO
 ├─ datastore  // DataStore (Session)
 ├─ dto        // Network models
 ├─ repository // Impl репозиториев

domain/
 ├─ model      // Business models
 ├─ repository // Interfaces
 ├─ usecase    // UseCases
 ├─ datastore  // SessionStore interface

presentation/
 ├─ auth       // Login / Register
 ├─ profile    // Profile
 ├─ search     // Search
 ├─ favorites  // Favorites
 ├─ navigation // NavGraph
 ├─ components // UI components
```

---

## 🧠 Single Source of Truth

В приложении строго соблюдён принцип **Single Source of Truth**:

* 🔐 **Сессия**
  → `DataStore (SessionStore)`
* ⭐ **Избранное**
  → `Room Database`
* 🖥 **UI-состояние**
  → `ViewModel + StateFlow`

UI **не хранит состояние**, а только подписывается на него.

---

## 🛠 Используемые технологии

### Android / UI

* **Jetpack Compose**
* **Material 3**
* **Navigation Compose**
* **Coil** (загрузка изображений)

### Архитектура и состояние

* **MVVM**
* **StateFlow / Flow**
* **Clean Architecture**
* **Single Source of Truth**

### Data

* **Retrofit 2**
* **Gson**
* **Room**
* **DataStore Preferences**

### DI

* **Dagger Hilt**
* **KSP**

---

## ⚙️ Сборка и запуск

### Требования

* Android Studio **Narwhal (2025.1.3)**
* Kotlin **K2**
* JDK **11+**
* Min SDK **26**

### Запуск через AndroidStudio

```bash
git clone https://github.com/your-username/webpa-mobile.git
cd webpa-mobile
```

Открыть проект в **Android Studio** → `Run`

### 🛠 Запуск через Gradle (без Android Studio)

```bash
./gradlew clean assembleDebug
```

APK будет доступен по пути:
```
app/build/outputs/apk/debug/app-debug.apk
```
---

## 🔐 Авторизация

* JWT access + refresh tokens
* refresh token хранится в `DataStore`
* защищённая навигация:

  * неавторизованный → auth-граф
  * авторизованный → main-граф

---

## 📂 Структура репозитория

* Чёткое разделение слоёв
* Нет зависимостей domain → data
* DI вынесен в отдельные модули
* Все экраны — Compose

---