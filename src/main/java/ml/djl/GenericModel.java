package ml.djl;

import ai.djl.MalformedModelException;
import ai.djl.Model;
import ai.djl.translate.TranslateException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenericModel {

    public void infer() throws MalformedModelException, IOException, TranslateException {
        Path modelDir = Paths.get("/home/danilo/IdeaProjects/JSL/huggingface-exports/models/pytorch/distilbert/distilbert-base-cased/distilbert_base.pt");
        Model model = Model.newInstance("pytorch-model");
        model.load(modelDir);
        System.out.println("Model loaded");
    }

}
