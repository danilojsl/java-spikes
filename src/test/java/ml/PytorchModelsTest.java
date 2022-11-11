package ml;

import ai.djl.MalformedModelException;
import ai.djl.translate.TranslateException;
import ml.djl.GenericModel;
import ml.djl.LinearRegression;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class PytorchModelsTest {

    @Test
    public void shouldInferLinearRegression() throws TranslateException, MalformedModelException, IOException {
        LinearRegression linearRegression = new LinearRegression();
        linearRegression.infer();
    }

    @Test
    public void shouldInferGenericModel() throws TranslateException, MalformedModelException, IOException {
        GenericModel genericModel = new GenericModel();
        genericModel.infer();
    }

}