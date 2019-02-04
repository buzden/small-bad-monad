# Small bad monad

An example of small bad monad implementation and its tests with cats-laws and specs2.

This example was created specially for [one issue in spec2](https://github.com/etorreborre/specs2/issues/720).
Implementation of the `tailRecM` function in the `Monad` instance for the example monad loops forever.
