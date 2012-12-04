import leon.Utils._

object PrimeHeuristic {
  def maybePrime(n: Int): Boolean = n match {
      case 2 * k     => false
      case 3 * k     => false
      case 6 * k - 1 => true
      case 6 * k + 1 => true
  }

}
