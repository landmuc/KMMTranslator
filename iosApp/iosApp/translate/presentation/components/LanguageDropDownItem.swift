//
//  LanguageDropDownItem.swift
//  iosApp
//
//  Created by Lars Uhland on 24.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LanguageDropDownItem: View {
    var language: UiLanguage
    var onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            HStack{
                // null check like ?.let{} in Kotlin
                if let image = UIImage(named: language.imageName.lowercased()) {
                    Image(uiImage: image)
                        .resizable()
                        .frame(width: 40, height: 40)
                        .padding(.trailing, 5)
                    Text(language.language.langName)
                        .foregroundColor(.textBlack)
                }
            }
        }
    }
}

#Preview {
    LanguageDropDownItem(
        language: UiLanguage(language: .japanese, imageName: "japanese"),
        onClick: {}
    )
}
