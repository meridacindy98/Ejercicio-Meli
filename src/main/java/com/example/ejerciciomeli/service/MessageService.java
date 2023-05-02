package com.example.ejerciciomeli.service;

import com.example.ejerciciomeli.exeption.MessageException;
import com.example.ejerciciomeli.model.CollectionSatellites;

import com.example.ejerciciomeli.model.Satellite;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class MessageService {

    public String decryptingMessage(List<List<String>> listMessages) throws MessageException {

        if(validateListMessage(listMessages)){
           return generateMessage(listMessages);
        } else {
            throw new MessageException("La cantidad de palabras en cada frase es distinta.");
        }

    }

    protected List<List<String>> getListMessages(CollectionSatellites satellites){
        return satellites.getSatellites().stream().map(Satellite::getMessage).collect(Collectors.toList());
    }

    private boolean validateListMessage(List<List<String>> listMessages){
        int firstMessage = listMessages.get(0).size();
        for (List<String> sublist : listMessages) {
            if (sublist.size() != firstMessage) {
                return false;
            }
        }
        return true;
    }

    private String generateMessage(List<List<String>> listMessages) throws MessageException {

        List<String> messageCompleted = new ArrayList<>();

        //recorrer las columnas
        for (int i = 0; i < listMessages.get(0).size(); i++){
            List<String> columWords = new ArrayList<>();
            //recorrer las filas
            for (List<String> listMessage : listMessages) {
                columWords.add(listMessage.get(i));
            }
            columWords.removeIf(a -> a.equals(""));
            if(columWords.stream().allMatch(columWords.get(0)::equals)){
                messageCompleted.add(columWords.get(0));
            } else {
                throw new MessageException("Error al intentar determinar el mensaje");
            }

        }
        return String.join(" ", messageCompleted);
    }

}
