using Microsoft.VisualStudio.TestTools.UnitTesting;
using RPS.Service;

namespace tests
{
    [TestClass]
    public class UnitTest1
    {
        /// <summary>
        /// Tests that the Wins or Ties are incremented
        /// </summary>
        [TestMethod]
        public void TestFirstWin()
        {
            RPSGameService gameService = new RPSGameService("test");
            var round = gameService.PlayRound(Selection.Paper);
            var game = gameService.EndGame();
            if (round.Winner > 0)
                Assert.AreEqual(game.Winner.Wins, 1);
            else
                Assert.AreEqual(game.Ties, 1);
        }

        [TestMethod()]
        [ExpectedExceptionAttribute(typeof(System.Exception))]
        public void InvalidCharTest()
        {
            RPSGameService gameService = new RPSGameService("test");
            var selection = gameService.TranslateEntry('t');
        }

        /// <summary>
        /// Tests that only valid keys are allowed via the TranslateEntry method
        /// </summary>
        /// <param name="key">key to test</param>
        [TestMethod()]
        [DataRow('r')]
        [DataRow('R')]
        [DataRow('p')]
        [DataRow('P')]
        [DataRow('s')]
        [DataRow('S')]
        public void ValidCharTest(char key)
        {
            RPSGameService gameService = new RPSGameService("test");
            var selection = gameService.TranslateEntry(key);
        }

        /// <summary>
        /// Tests the logic for determining winner. Note that the game property and DetermineWin methods were made public
        /// for the purposes of this test, otherwise they would have been private.
        /// </summary>
        /// <param name="p1key">Player 1 selection</param>
        /// <param name="p2key">Player 2 selection</param>
        /// <param name="expectedResult">Expected winner. 0=tie, 1=p1, 2=p2--></param>
        [TestMethod()]
        [DataRow(Selection.Paper, Selection.Paper, 0)]
        [DataRow(Selection.Paper, Selection.Rock, 1)]
        [DataRow(Selection.Paper, Selection.Scissors, 2)]
        [DataRow(Selection.Scissors, Selection.Paper, 1)]
        [DataRow(Selection.Rock, Selection.Scissors, 1)]
        public void CoreLogicTests(Selection p1key, Selection p2key, int expectedResult) {
            RPSGameService gameService = new RPSGameService("test");
            gameService.game.Player1.Selection = p1key;
            gameService.game.Player2.Selection = p2key;
            Assert.AreEqual(gameService.DetermineWin(), expectedResult);
        }

    }
}
