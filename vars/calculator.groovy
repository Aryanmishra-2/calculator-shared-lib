import com.company.utils.CleanWorkspace
import com.company.utils.CalculatorUtils

def call() {
    stage('Clean Workspace') {
        new CleanWorkspace(this).run()
    }
}

def add(a, b) {
    CalculatorUtils.add(a as int, b as int)
}

def sub(a, b) {
    CalculatorUtils.sub(a as int, b as int)
}

def mul(a, b) {
    CalculatorUtils.mul(a as int, b as int)
}

def div(a, b) {
    CalculatorUtils.div(a as int, b as int)
}
