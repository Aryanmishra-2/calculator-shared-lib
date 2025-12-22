import com.company.utils.*

def call() {
    stage('Clean Workspace') {
        new CleanWorkspace(this).run()
    }
}

def add(a, b) {
    return CalculatorUtils.add(a as int, b as int)
}

def sub(a, b) {
    return CalculatorUtils.sub(a as int, b as int)
}

def mul(a, b) {
    return CalculatorUtils.mul(a as int, b as int)
}

def div(a, b) {
    return CalculatorUtils.div(a as int, b as int)
}
