
package api

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable

class petStoreClient {

    private def sendRequest(String method, String url, String body = null) {
        RequestObject request = new RequestObject()

        request.setRestUrl(url)
        request.setRestRequestMethod(method)

        List<TestObjectProperty> headers = []

        headers.add(new TestObjectProperty(
            'Accept',
            ConditionType.EQUALS,
            'application/json',
            true
        ))

        if (body != null) {
            headers.add(new TestObjectProperty(
                'Content-Type',
                ConditionType.EQUALS,
                'application/json',
                true
            ))

            request.setBodyContent(
                new HttpTextBodyContent(
                    body,
                    'UTF-8',
                    'application/json'
                )
            )
        }

        request.setHttpHeaderProperties(headers)

        return WS.sendRequest(request)
    }

    @Keyword
    def getPet(String petId) {
        return sendRequest(
            'GET',
            "${GlobalVariable.baseUrl}/pet/${petId}"
        )
    }

    @Keyword
    def createPet(String body) {
        return sendRequest(
            'POST',
            "${GlobalVariable.baseUrl}/pet",
            body
        )
    }

    @Keyword
    def updatePet(String body) {
        return sendRequest(
            'PUT',
            "${GlobalVariable.baseUrl}/pet",
            body
        )
    }

    @Keyword
    def deletePet(String petId) {
        return sendRequest(
            'DELETE',
            "${GlobalVariable.baseUrl}/pet/${petId}"
        )
    }
}
