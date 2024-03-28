package com.landmuc.kmmtranslator.voice_to_text.domain

import com.landmuc.kmmtranslator.core.domain.util.CommonStateFlow

interface VoiceToTextParser {
    val state: CommonStateFlow<VoiceToTextParserState>
    fun startListening(languageCode: String)
    fun stopListening()
    fun cancel()
    fun reset()
}