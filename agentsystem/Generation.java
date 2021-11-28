package agentsystem;

public abstract class Generation<T> implements IGeneration<T> {
    private final int nbAgents;

    public Generation(int nbAgents) {
        this.nbAgents = nbAgents;
    }

    @Override
    public int nbAgents() {
        return nbAgents;
    }

    @Override
    public abstract IGeneration<T> clone();

    @Override
    public abstract T get(int i);
}
