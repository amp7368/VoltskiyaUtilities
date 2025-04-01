package voltskiya.apple.utilities.chance.weight;

import apple.utilities.structures.Pair;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import voltskiya.apple.utilities.chance.Chance;

public class ChanceWeightedChoice<T> extends Chance {

    private final Function<T, Pair<T, Double>> weighFn;

    public ChanceWeightedChoice(Function<T, Double> weighFn) {
        this.weighFn = (choice) -> new Pair<>(choice, weighFn.apply(choice));
    }

    @NotNull
    public T choose(@NotNull Collection<T> choices) throws IllegalArgumentException {
        if (choices.isEmpty()) throw new IllegalArgumentException("Collection<T> 'choices' is empty");
        List<Pair<T, Double>> weights = choices.stream().map(weighFn).toList();
        Double sum = weights.stream().map(Pair::getValue).reduce(Double::sum).orElse(0d);
        double choice = this.random().nextDouble() * sum;
        for (Pair<T, Double> weight : weights) {
            choice -= weight.getValue();
            if (choice <= 0) return weight.getKey();
        }
        throw new IllegalStateException(
            "A sum of " + sum + " with a choice of " + choice + " gave no result even though 'choices' is NOT empty");
    }

}
