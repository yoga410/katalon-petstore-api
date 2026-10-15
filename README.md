# Katalon Petstore API Testing

Proyek otomatisasi pengujian REST API Petstore (`https://petstore.swagger.io/v2`) menggunakan **Katalon Studio v10.4.3**.

---

## 📁 Struktur Folder

```plaintext
├── Profiles/
│   └── default.glbl                 # Global Variables (baseUrl, petId, petStatus)
├── Object Repository/
│   └── Pet/                         # Web Service Objects (POST, GET, PUT, DELETE)
├── Keywords/
│   └── com/petstore/PetKeywords.groovy # Helper validasi & request handler
├── Test Cases/
│   ├── TC_POST             # [Positif] POST /pet
│   ├── TC_GET              # [Positif] GET /pet/{petId}
│   ├── TC_PUT              # [Positif] PUT /pet
│   ├── TC_DELETE           # [Positif] DELETE /pet/{petId}
│   ├── TC_POSTNegative     # [Negatif] POST /pet/-11ab -> 400 Bad Request
│   └── TC_E2E              # [E2E] Create -> Get -> Update -> Delete
└── Test Suites/
    └── TC_regresi.ts                # Test Suite gabungan seluruh skenario
```

---

## ⚙️ Global Variables (Profile: `profile`)

Dikelola melalui Profiles/profile.glbl:

baseUrl : 'https://petstore.swagger.io/v2' (Base URL Swagger Petstore API)
petId : '0' (diisi dinamis saat testing)
petName : 'initPet' (Nama awal pet untuk pembuatan data)
updatePetName : 'updatePet' (Nama baru pet saat skenario update)

---

## 🔗 Integrasi Object Repository & Keyword

Setiap Test Case mengeksekusi request dengan melewatkan variabel dinamis ke **Object Repository** melalui **Custom Keyword**:

```groovy
// Contoh pemanggilan di Test Case via Keyword & Object Repository:
CustomKeywords.'com.petstore.PetKeywords.sendRequestWithVars'(
    findTestObject('Object Repository/Pet/Get Pet By ID', [
        ('baseUrl') : GlobalVariable.baseUrl,
        ('petId')   : GlobalVariable.petId,
        ('petName') : GlobalVariable.updatePetName
    ]), 
    200 // Expected Status Code
)
```

---

## 🧪 Skenario Pengujian

| Tipe | Test Case | Endpoint | Ekspektasi |
| :--- | :--- | :--- | :--- |
| **Positif** | `TC_POST` | `POST /pet` | `200 OK`, Pet terbuat |
| **Positif** | `TC_GET`| `GET /pet/{id}` | `200 OK`, Data sesuai |
| **Positif** | `TC_PUT` | `PUT /pet` | `200 OK`, Status terupdate |
| **Positif** | `TC_DELETE` | `DELETE /pet/{id}`| `200 OK`, Pet terhapus |
| **Negatif** | `TC_POSTNegative` | `POST /pet/-11ab` | `400 Bad Request` |
| **E2E** | `TC_E2E` | *Multi-endpoint* | Siklus penuh: Create $\to$ Read $\to$ Update $\to$ Delete $\to$ Verify 404 |

---

## 🚀 Cara Menjalankan

1. **Katalon GUI:** Buka **`Test Suites/TC_regresi`** $\to$ Pilih Profile **`profile`** $\to$ Klik **Run** (`Web Service`).
