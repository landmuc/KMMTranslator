package com.landmuc.kmmtranslator.voice_to_text.data

import com.landmuc.kmmtranslator.core.domain.util.CommonStateFlow
import com.landmuc.kmmtranslator.core.domain.util.toCommonStateFlow
import com.landmuc.kmmtranslator.voice_to_text.domain.VoiceToTextParser
import com.landmuc.kmmtranslator.voice_to_text.domain.VoiceToTextParserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeVoiceToTextParser: VoiceToTextParser {

    private val _state = MutableStateFlow(VoiceToTextParserState())
    override val state: CommonStateFlow<VoiceToTextParserState>
        get() = _state.toCommonStateFlow()

    var voiceResult = "test result"

    override fun startListening(languageCode: String) {
        _state.update { it.copy(
            result = "",
            isSpeaking = true
        ) }
    }

    override fun stopListening() {
        _state.update { it.copy(
            result =  voiceResult,
            isSpeaking = false
        ) }
    }

    override fun cancel() = Unit

    override fun reset() {
        _state.update { VoiceToTextParserState() }
    }
}