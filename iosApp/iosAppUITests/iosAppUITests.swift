//
//  iosAppUITests.swift
//  iosAppUITests
//
//  Created by Lars Uhland on 07.04.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import XCTest

final class iosAppUITests: XCTestCase {

    private var app: XCUIApplication! // ! -> do not initialize this app right here. Initialization before every test in setUpWithError()
    
    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.

        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
        app = XCUIApplication()
        app.launchArguments = ["isUiTesting"] // launch argument for injecting fake dependencies in our production code
        app.launch()
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testRecordAndTranslate() {
        app.buttons["Record audio"].tap() // go to VoiceToTextScreen
        
        app.buttons["Voice recorder button"].tap() // start recording
        app.buttons["Voice recorder button"].tap() // stop recording
        
        XCTAssert(app.staticTexts["test result"].waitForExistence(timeout: 2))
        
        app.buttons["Voice recorder button"].tap() // apply text result to TranslateScreen
        
        XCTAssert(app.textViews["test result"].waitForExistence(timeout: 2)) // textView to check TextEditor for test text
        
        app.buttons["TRANSLATE"].tap()
        
        XCTAssert(app.staticTexts["test result"].waitForExistence(timeout: 2))
        XCTAssert(app.staticTexts["test translation"].waitForExistence(timeout: 2))
    }
}
