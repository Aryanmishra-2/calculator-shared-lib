import com.company.utils.*

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

def saveResult(content) {
    new ArtifactWriter(this).saveResult(content)
}

def notifyEmail(success, recipient) {
    new Email(this).mail(success, recipient)
}
