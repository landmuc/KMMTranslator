package com.landmuc.kmmtranslator.translate.data.remote

import com.landmuc.kmmtranslator.core.domain.language.Language
import com.landmuc.kmmtranslator.translate.domain.translate.TranslateClient

class FakeTranslateClient: TranslateClient {

    var translatedText = "test translation"

    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String {
        return translatedText
    }
}