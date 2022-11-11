package ml.djl;

import ai.djl.MalformedModelException;
import ai.djl.Model;
import ai.djl.inference.Predictor;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.translate.Batchifier;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LinearRegression {

    public void infer() throws MalformedModelException, IOException, TranslateException {
        Path modelDir = Paths.get("/home/danilo/IdeaProjects/MySpikes/spike_nn/models");
        Model model = Model.newInstance("model1.zip");
        model.load(modelDir);
        System.out.println("Model loaded");
        Translator<Float, Float> translator = new Translator<Float, Float>(){
            @Override
            public NDList processInput(TranslatorContext ctx, Float input) {
                NDManager manager = ctx.getNDManager();
                NDArray array = manager.create(new float[] {input});
                return new NDList (array);
            }

            @Override
            public Float processOutput(TranslatorContext ctx, NDList list) {
                NDArray temp_arr = list.get(0);
                return temp_arr.getFloat();
            }

            @Override
            public Batchifier getBatchifier() {
                // The Batchifier describes how to combine a batch together
                // Stacking, the most common batchifier, takes N [X1, X2, ...] arrays to a single [N, X1, X2, ...] array
                return Batchifier.STACK;
            }
        };

        Predictor<Float, Float> predictor = model.newPredictor(translator);
        Float output = predictor.predict((float) 54545454554f);
        System.out.println("Result: " + output.toString());
    }

}
