package com.landmuc.kmmtranslator.translate.domain.translate

import com.landmuc.kmmtranslator.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}