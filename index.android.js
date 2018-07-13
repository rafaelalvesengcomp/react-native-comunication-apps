'use strict'

import React, { Component } from 'react';
import { NativeModules } from 'react-native'
// name as defined via ReactContextBaseJavaModule's getName

export default class ComunicationApps extends Component{

    static setup(name, packagesName, title, progress, tags, isAudio, isVideo){
        NativeModules.ComunicationApps.setup(
            name, packagesName, title, progress, tags, isAudio, isVideo
        );
    }

    static getInformations(){
        NativeModules.ComunicationApps.getInformations();
    }

    static setProgress(progress){
        NativeModules.ComunicationApps.setProgress(progress);
    }

    static test(){
        console.warn('oi');
    }

    render(){
        return null;
    }
}

