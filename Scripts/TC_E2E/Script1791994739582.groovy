import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// pet yang akan dibuat
String petId = '91111'
String petName = 'FlameE2E'
String petStatus = 'E2E_pet'

// ================================
// STEP 1: POST - CREATE PET
// ================================
// body JSON
String createBody = """
{
    "id": ${petId},
    "category": {
        "id": 1,
        "name": "dog"
    },
    "name": "${petName}",
    "photoUrls": [],
    "tags": [],
    "status": "${petStatus}"
}
"""

// Panggil Keyword POST
def postResponse = CustomKeywords.'api.petStoreClient.createPet'(createBody)

WS.verifyResponseStatusCode(postResponse, 200)


// ================================
// STEP 1: GET - Verify PET
// ================================

// Panggil Keyword GET
def getResponse  = CustomKeywords.'api.petStoreClient.getPet'(petId)

WS.verifyResponseStatusCode(getResponse, 200)



// ================================
// STEP 1: PUT - update PET
// ================================
// body JSON
String updateBody = """
{
    "id": ${petId},
    "category": {
        "id": 1,
        "name": "dog"
    },
    "name": "${petName}",
    "photoUrls": [],
    "tags": [],
    "status": "${petStatus}"
}
"""

// panggil keyword
def putResponse = CustomKeywords.'api.petStoreClient.updatePet' (updateBody)
WS.verifyResponseStatusCode(putResponse, 200)


// ================================
// STEP 1: DELETE - delete PET
// ================================
// panggil keyword
def deleteResponse = CustomKeywords.'api.petStoreClient.deletePet' (petId)

WS.verifyResponseStatusCode(deleteResponse, 200)


println('E2E selesai')