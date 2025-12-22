# 📱 Webpa Mobile — Android приложение для поиска товаров с маркетплейсов

Android-приложение для поиска товаров с маркетплейсов, добавления в избранное и управления пользовательским профилем.
Проект реализован с использованием **Clean Architecture**, **Jetpack Compose** и **MVVM**.

---

## 🚀 Возможности

* 🔍 Поиск товаров
* ⭐ Добавление / удаление из избранного
* 👤 Регистрация и авторизация пользователя
* 🔐 JWT-авторизация + refresh token
* 📦 Профиль пользователя
* 💾 Сохранение сессии (DataStore)
* ⚡ Реактивный UI (StateFlow)
* 🧭 Навигация с авторизационными графами

---

## 🎥 Демонстрация работы


https://github.com/user-attachments/assets/a0e1a6b4-4797-48d2-8c8b-1fb8fc47560f

---

## 🏗 Архитектура

Проект построен по принципам **Clean Architecture + MVVM**.

### Слои

```
presentation/
 ├─ screens (Compose UI)
 ├─ viewmodels
 └─ navigation

domain/
 ├─ models
 ├─ usecases
 ├─ repository interfaces
 └─ datastore interface

data/
 ├─ api (Retrofit)
 ├─ repository implementations
 ├─ mappers
 ├─ datastore (SessionStore)
 └─ dto
```

### Поток данных

```
UI (Compose)
 ↓
ViewModel (StateFlow)
 ↓
UseCase
 ↓
Repository (interface)
 ↓
Data layer (API / DataStore)
```

---

## 🧩 Используемые технологии

### Android

* **Kotlin**
* **Jetpack Compose**
* **Navigation Compose**
* **Material 3**
* **StateFlow / Coroutines**

### Архитектура

* **MVVM**
* **Clean Architecture**
* **Single Source of Truth**

### DI / Data

* **Hilt**
* **Retrofit**
* **Room**
* **DataStore**

### Backend

* **Spring Boot** [SidereaH/WebPA-Back](https://github.com/SidereaH/WebPA-Back/tree/latest)
* **JWT (access + refresh)**
* **PostgreSQL**

---

## 🔐 Авторизация

* Access token + Refresh token
* Refresh хранится в DataStore
* UserId сохраняется при логине
* При старте приложения определяется `isAuthorized`
* Навигация делится на:

    * `auth graph`
    * `main graph`

---

## 🧭 Навигация

```
RootNavGraph
 ├─ auth
 │   ├─ login
 │   └─ register
 └─ main
     ├─ search
     ├─ favorites
     ├─ profile
     └─ product_details/{id}
```

---

## ⚙️ Сборка и запуск

### Требования

* Android Studio Hedgehog / Iguana
* JDK 17
* Android SDK 34+

### Запуск

```bash
git clone https://github.com/username/webpa-mobile.git
cd webpa-mobile
```

1. Открыть проект в Android Studio
2. Sync Gradle
3. Запустить на эмуляторе или устройстве

---

## 📂 Структура репозитория

```
app/
 ├─ data/
 ├─ domain/
 ├─ presentation/
 └─ di/

docs/
 ├─ screenshots
 └─ demo.mp4
```

---

## 🧠 Что реализовано осознанно

* ❌ Domain слой **не зависит от data**
* ❌ UI не знает про Retrofit / DataStore
* ✅ UseCase = 1 действие
* ✅ Навигация изолирована
* ✅ Состояния экранов (Loading / Error / Success)

---

## 📝 Коммиты

Коммиты оформлены осмысленно:

```
feat: add auth flow with jwt
feat: implement profile screen
feat: favorites feature with room
refactor: clean navigation graph
fix: session restore on app start
```

---

## 📌 GitHub Issues

* `Demo` — скринкаст работы приложения
* `Architecture` — описание архитектурных решений
* `UI polish` — доработка интерфейса

---

## 👤 Автор

**[Твоё имя]**
Android Developer
Telegram: @username
GitHub: [https://github.com/username](https://github.com/username)

---

## ✅ Итог

Проект демонстрирует:

* уверенное владение Android-стеком
* понимание архитектуры
* работу с авторизацией
* современный UI на Compose

---

Если хочешь — дальше можем:

* ✨ дополировать UI (анимации, shimmer)
* 🧪 добавить unit-тесты
* 📦 подготовить проект под портфолио / собес
* 🧠 написать architecture decision record (ADR)

скажи — идём дальше или **фиксируем как финал** 🏁
