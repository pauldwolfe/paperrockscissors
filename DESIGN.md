Quick implementation notes:

* Python with no external dependencies. Latest version.

* This is excessively abstracted for a problem this size, just to demonstrate
some patterns that might apply to larger cases, which I guess is part of the
point.

* Input data validation and rules applications are in their own classes, though
in this case simple they are really just simple functions in disguise. But e.g.
a large game with complex rules might benefit from this separation and have state
and require helpers etc.

* Enums and DataClasses for communicating state, most game logic is independent
of user input strings (these are all cleaned into enums in one place) 

* Game class is decoupled from input/output, handy for testing. You could also
use it for example in a webserver without changing any of the game layer.
Downside is that this turns the logic inside out as the I/O driver has to be
external. Tradeoff.

* One place where it was handy to inject behavior is the 'strategy' given given
to game instance initialization. Makes is easier to test (no randomness, no
mocking). Abstracting out both the player and computer as instances of a common
class would be the next step (e.g. player vs player), but not explored here.

Note that a direct implementation of the game could be just one or two functions
and would be *much* shorter as well as easier to understand.
