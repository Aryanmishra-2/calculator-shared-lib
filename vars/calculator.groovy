import com.company.utils.CalculatorUtils

def call(String operation, int a, int b) {

    switch (operation) {
        case "add":
            return CalculatorUtils.add(a, b)

        case "sub":
            return CalculatorUtils.sub(a, b)

        case "mul":
            return CalculatorUtils.mul(a, b)

        case "div":
            return CalculatorUtils.div(a, b)

        default:
            error "Invalid operation: ${operation}"
    }
}
